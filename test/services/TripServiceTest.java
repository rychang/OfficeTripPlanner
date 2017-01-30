package services;

import conf.AppConf;
import conf.TestDataConf;
import models.Trip;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.inject.Inject;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by kohaus on 1/27/17.
 */
@ContextConfiguration(classes = {
        AppConf.class, TestDataConf.class
})

public class TripServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Inject private TripService tripService;

    public Integer createTrip() {
        Set<Trip> trip = tripService.getAllStoredTrips();
        Trip addTrip = new Trip();
        if (trip == null || trip.isEmpty()) {
            addTrip.setLocation("Mercury, NV");
            addTrip.setStartDate("2017-02-14");
            addTrip.setEndDate("2017-02-28");
            tripService.save(addTrip);
        }
        return addTrip.getId();
    }

    @Test
    public void getTripByIdTest(){
            Trip idTrip = new Trip();
            idTrip.setLocation("Chester, CT");
            idTrip.setStartDate("2017-02-14");
            idTrip.setEndDate("2017-02-28");
            Integer testInt = tripService.save(idTrip);
            Trip test = tripService.getTripById(testInt);
            assertEquals(idTrip.getLocation(),test.getLocation());
    }

    @Test
    public void saveTest(){
            Integer testID = createTrip();
            //Verify the save
            Trip testTrip = tripService.getTripById(testID);
            assertFalse(testTrip == null);
    }

    @Test
    public void getAllStoredTripsTest(){
            createTrip();
            Set<Trip> trips = tripService.getAllStoredTrips();
            assertFalse(trips.isEmpty());
    }
}
