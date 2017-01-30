package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Trip;
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

    @Inject ObjectMapper objectMapper;

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

    public JsonNode getJson(Trip trip) {
        String urlStartDate = trip.getStartDate().replaceAll("-", "");
        urlStartDate = urlStartDate.substring(4);

        String urlEndDate = trip.getEndDate().replaceAll("-","");
        urlEndDate = urlEndDate.substring(4);

        String Loc = trip.getLocation().replaceAll(" ","_");
        String stateAbv = Loc.substring(Loc.lastIndexOf(",")+2);
        String city = Loc.substring(0,Loc.lastIndexOf(","));

        String all = "";
        JsonNode root;
        try {
            URL weather = new URL("http://api.wunderground.com/api/343dc8e3f2c2c4d7/planner_"+ urlStartDate + urlEndDate
                    + "/q/"+ stateAbv +"/" + city +".json");
            URLConnection cc = weather.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(cc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                all += inputLine;
            in.close();
            root = objectMapper.readTree(all);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return root;
    }
}
