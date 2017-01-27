package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by kohaus on 1/27/17.
 */
@Service
public class WeatherServiceImpl implements WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(TripServiceImpl.class);


    public String getLowAvg(JsonNode obj) {
        return obj.get("trip").get("temp_low").get("avg").get("F").asText();
    }

    public String getHighAvg(JsonNode obj) {
         return obj.get("trip").get("temp_high").get("avg").get("F").asText();
    }

    public String getPrecipPercentage(JsonNode obj) {
        return obj.get("trip").get("chance_of").get("chanceofprecip").get("percentage").asText();
    }

    public String getCloudCover(JsonNode obj){
        String cloudCover = obj.get("trip").get("cloud_cover").get("cond").asText();
        return cloudCover.substring(0, 1).toUpperCase() + cloudCover.substring(1);
    }

}
