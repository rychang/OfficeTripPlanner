package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Trip;
import models.TripForm;
import play.mvc.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.inject.Inject;

import play.data.Form;
import play.mvc.*;

import services.TripService;
import services.WeatherService;
import views.html.*;

@org.springframework.stereotype.Controller
public class Application extends Controller {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Inject
    private TripService tripService;

    @Inject
    private WeatherService weatherService;

    @Inject
    private ObjectMapper objectMapper;

    public Result index() {
        return ok(index.render(Form.form(TripForm.class)));
    }

    public Result tripMaker() {
        Form<TripForm> formData = Form.form(TripForm.class).bindFromRequest();

        if(formData.hasErrors() || formData.hasGlobalErrors()){
            logger.error(String.valueOf(formData.errors()));
            return badRequest(index.render(Form.form(TripForm.class)));
        }
        TripForm tripForm = formData.get();
        Trip trip = new Trip();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate, endDate;

        try {
            startDate = df.parse(tripForm.getStartDate());
            endDate = df.parse(tripForm.getEndDate());

            long diff = endDate.getTime() - startDate.getTime();
            int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
            if (diffDays < 0){
                formData.reject("Your end date came before your start date");
                return badRequest(index.render(formData));
            } else if (diffDays>30) {
                formData.reject("Your date range is too large for our service");
                return badRequest(index.render(formData));
            }

        } catch (ParseException e){
            formData.reject("There was an error ¯\\_(ツ)_/¯");
            return badRequest(index.render(Form.form(TripForm.class)));
        }

        trip.setLocation(tripForm.getOfficeLoc());
        trip.setStartDate(tripForm.getStartDate());
        trip.setEndDate(tripForm.getEndDate());


        try {
            tripService.save(trip);
        } catch (Exception e){
            formData.reject("You are already booked for at least one of those dates");
            return badRequest(index.render(formData));
        }

        return ok(index.render(Form.form(TripForm.class)));
    }

    public Result getAllTrips() {
        Set<Trip> tripSet = tripService.getAllStoredTrips();

        return ok(trip.render(tripSet));
    }

    public Result getWeather(Integer id) {
        Trip trip = tripService.getTripById(id);

        String urlStartDate = trip.getStartDate().replaceAll("-", "");
        urlStartDate = urlStartDate.substring(4);

        String urlEndDate = trip.getEndDate().replaceAll("-","");
        urlEndDate = urlEndDate.substring(4);

        String Loc = trip.getLocation().replaceAll(" ","_");
        String stateAbv = Loc.substring(Loc.lastIndexOf(",")+2);
        String city = Loc.substring(0,Loc.lastIndexOf(","));

        String all = "";
        JsonNode root;
        try {
            URL weather = new URL("http://api.wunderground.com/api/343dc8e3f2c2c4d7/planner_"+ urlStartDate + urlEndDate
                    + "/q/"+ stateAbv +"/" + city +".json");
            URLConnection cc = weather.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(cc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                all += inputLine;
            in.close();
            root = objectMapper.readTree(all);
        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }

        return ok(weather.render(weatherService.getHighAvg(root), weatherService.getLowAvg(root),weatherService.getPrecipPercentage(root),weatherService.getCloudCover(root), trip));
    }

}
