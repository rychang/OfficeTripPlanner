package services;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by kohaus on 1/27/17.
 */
public interface WeatherService {
    /**
     * Get the average low temp for the given date window via an API call
     * @param JsonNode obj, a JSON object retrieved from the API
     * @return String parsed from the API JSON that is the average low temp for the date period
     */
    String getLowAvg(JsonNode obj);

    /**
     * Get the average high temp for the given date window via an API call
     * @param JsonNode obj, a JSON object retrieved from the API
     * @return String parsed from the API JSON that is the average high temp for the date period
     */
    String getHighAvg(JsonNode obj);

    /**
     * Get the chance of perciptation for the given date window, reflected as a percentage
     * @param JsonNode obj, a JSON object retrieved from the API
     * @return String parsed from the API JSON that is the precipitation percentage for the date period
     */
    String getPrecipPercentage(JsonNode obj);

    /**
     * Get the average cloud coverage for the given date window
     * @param JsonNode obj, a JSON object retrieved from the API
     * @return String parsed from the JSON that is the average Cloud Coverage for the date window
     */
    String getCloudCover(JsonNode obj);
}
