package services;

import models.Office;

import java.util.List;

public interface OfficeService {

    /**
     * Add cities to database
     */
    void initializeDb();

    /**
     * List of cities that have a SoFi office that is used to populate the dropdown menu
     *
     * @return - list of strings of cities that contain a SoFi Office
     */
    List<String> sofiOfficeLocations();

    /**
     * Get the list of office locations in the database
     *
     * @return - List of all offices
     */
    List<Office> getAllLocations();

    /**
     * Get the office location given the city name
     *
     * @param locationName - name of city in database
     */
    Office getLocation(String locationName);


}
