package controllers;


import play.mvc.Controller;
import models.Foo;
import models.FooForm;

import services.FooService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import play.data.Form;
import play.mvc.*;

import views.html.*;

@org.springframework.stereotype.Controller
public class TestApplication extends Controller {

    Logger logger = LoggerFactory.getLogger(TestApplication.class);


    @Inject
    private FooService fooService;

    public Result foo() {
        return ok(foo.render());
    }

    public Result bar() {

        Form <FooForm> form = Form.form(FooForm.class).bindFromRequest();

        if(form.hasErrors() || form.hasGlobalErrors()){
                logger.error(String.valueOf(form.errors()));
                return badRequest(foo.render());
        }


        FooForm fooForm = form.get();
        Foo food = new Foo(fooForm.getRandomNumber(), fooForm.getDate1(), fooForm.getDate2());
        fooService.save(food);

        return ok(foo.render());
    }


 }
