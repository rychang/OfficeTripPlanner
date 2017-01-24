package services;

import models.Trip;

import java.util.Set;

public interface TripService {
    /**
     * Gets a Trip instance by its primary key identifier.
     *
     * @param An identifying integer presumably associated with a trip.
     * @return Trip instance, if any, associated with the parameter.
     */
    Trip getTripById(Integer id);

    /**
     * Saves (persists) a Trip.
     *
     * @param A Trip to be saved
     * @return Auto generated, identifying integer.
     */
    Integer save(Trip trip);

    /**
     * Get all stored Trips
     * @return Set<Trip>
     */
    Set<Trip> getAllStoredTrips();
}

