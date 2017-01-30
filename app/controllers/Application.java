package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Trip;
import models.TripForm;

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
import play.mvc.Result;

import services.TripService;
import services.WeatherService;

import views.html.index;
import views.html.trip;
import views.html.weather;

@org.springframework.stereotype.Controller
public class Application extends play.mvc.Controller {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Inject private TripService tripService;

    @Inject private WeatherService weatherService;

    public Result index() {
        return ok(index.render(Form.form(TripForm.class)));
    }

    public Result tripMaker() {
        Form<TripForm> formData = Form.form(TripForm.class).bindFromRequest();

        if (formData.hasErrors() || formData.hasGlobalErrors()) {
            logger.debug(String.valueOf(formData.errors()));
            formData.reject("Form has errors");
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
            logger.error("Un-parsable date string submitted. Start date: {}, End date: {}", tripForm.getStartDate(), tripForm.getEndDate());
            formData.reject("Dates not parsable");
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
        JsonNode root = weatherService.getJson(trip);

        return ok(weather.render(weatherService.getHighAvg(root), weatherService.getLowAvg(root),weatherService.getPrecipPercentage(root),weatherService.getCloudCover(root), trip));
    }

}
