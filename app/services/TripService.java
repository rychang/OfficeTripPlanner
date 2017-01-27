package services;

import models.Trip;

import java.util.Set;

public interface TripService {
    /**
     * Gets a Trip instance by its primary key identifier.
     *
     * @param id Primary key of trip.
     * @return trip instance, if any, for the given key.
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

