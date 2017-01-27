package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import conf.TestDataConf;
import org.junit.Test;
import static org.junit.Assert.*;

import conf.AppConf;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;



import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;

/**
 * Created by kohaus on 1/27/17.
 */


@ContextConfiguration(classes = {
        AppConf.class, TestDataConf.class
})

public class WeatherServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private WeatherService weatherService;

    @Test
    public void getLowAvgTest() {
        try{
            URL weather = new URL("http://api.wunderground.com/api/343dc8e3f2c2c4d7/planner_01270129/q/OK/Oklahoma_City.json");
            JsonNode test = objectMapper.readTree(weather);
            assertTrue(weatherService.getHighAvg(test).equals("61"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getHighAvgTest() {
        try{
            URL weather = new URL("http://api.wunderground.com/api/343dc8e3f2c2c4d7/planner_01270129/q/OK/Oklahoma_City.json");
            JsonNode test = objectMapper.readTree(weather);
            assertTrue(weatherService.getLowAvg(test).equals("33"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getPrecipPercentageTest() {
        try{
            URL weather = new URL("http://api.wunderground.com/api/343dc8e3f2c2c4d7/planner_01270129/q/OK/Oklahoma_City.json");
            JsonNode test = objectMapper.readTree(weather);
            assertTrue(weatherService.getPrecipPercentage(test).equals("20"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getCloudCoverTest() {
        try{
            URL weather = new URL("http://api.wunderground.com/api/343dc8e3f2c2c4d7/planner_01270129/q/OK/Oklahoma_City.json");
            JsonNode test = objectMapper.readTree(weather);
            assertTrue(weatherService.getCloudCover(test).equals("Mostly sunny"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
