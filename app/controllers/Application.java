package controllers;

import models.Trip;
import models.TripForm;

import services.TripService;
import services.OfficeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;
import java.util.Set;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private TripService tripService;

    @Autowired
    private OfficeService officeService;

}
