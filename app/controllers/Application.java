package controllers;

import models.Trip;
import models.TripForm;

import services.TripService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import play.data.Form;
import play.mvc.*;

import views.html.*;

@org.springframework.stereotype.Controller
public class Application extends Controller {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Inject private TripService tripService;

    /*
    public Result index() {
        officeService.initializeDb();
        List<String> officeLocs;
        officeLocs = officeService.sofiOfficeLocations();
        return ok(index.render(""));
    }

    public Result tripMaker() {
        //Get information submitted into the form
        Form<TripForm> formData = Form.form(TripForm.class).bindFromRequest();
        if (formData.hasErrors()) {
            logger.error("Input Form has errors.");
        }
        String officeLoc = formData.get().getOfficeLoc();
        return ok(index.render("hello, SoFi"));
    }
    /**
    public Result getTrip(){

    }

    public Result getAllTrips(){

    }
    **/
}
