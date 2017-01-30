package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import models.Trip;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import javax.inject.Inject;
import java.io.IOException;

import org.apache.http.client.fluent.Request;

import static org.reflections.Reflections.log;

/**
 * Created by kohaus on 1/27/17.
 */
@Service
public class WeatherServiceImpl implements WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(TripServiceImpl.class);

    private static final int TIMEOUT_MS = 5*1000;

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

        String url = "http://api.wunderground.com/api/343dc8e3f2c2c4d7/planner_"+ urlStartDate + urlEndDate
                + "/q/"+ stateAbv +"/" + city +".json";

        JsonNode root;
        try {
            log.trace("calling preciseid webservice: {}", url);
            root = Request.Get(url)
                    .socketTimeout(TIMEOUT_MS)
                    .connectTimeout(TIMEOUT_MS)
                    .execute()
                    .handleResponse(responseHandler);
        } catch (IOException e) {
            log.error("Unable to connect to preciseid service for request: {}", url, e);
            return null;
        }
        return root;
    }

    private ResponseHandler<JsonNode> responseHandler = (org.apache.http.HttpResponse response) -> {
        StatusLine statusLine = response.getStatusLine();
        HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() >= 300 || entity == null || entity.getContentLength() == 0) {
            return JsonNodeFactory.instance.nullNode();
        }

        return new ObjectMapper().readValue(EntityUtils.toString(entity), JsonNode.class);
    };

}
