package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import conf.TestDataConf;
import org.junit.Test;

import conf.AppConf;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

/**
 * Created by kohaus on 1/27/17.
 */

@ContextConfiguration(classes = {
        AppConf.class, TestDataConf.class
})

public class WeatherServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Inject private ObjectMapper objectMapper;

    @Inject private WeatherService weatherService;

    @Test
    public void getLowAvgTest() {
        try {
            JsonNode test = objectMapper.readTree(new File("test/resources/test.json"));
            System.out.println("Low: " + weatherService.getLowAvg(test));
            assertTrue(weatherService.getLowAvg(test).equals("32"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getHighAvgTest() {
        try {
            JsonNode test = objectMapper.readTree(new File("test/resources/test.json"));
            System.out.println("High: " + weatherService.getHighAvg(test));
            assertTrue(weatherService.getHighAvg(test).equals("61"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getPrecipPercentageTest() {
        try {
            JsonNode test = objectMapper.readTree(new File("test/resources/test.json"));
            assertTrue(weatherService.getPrecipPercentage(test).equals("17"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getCloudCoverTest() {
        try {
            JsonNode test = objectMapper.readTree(new File("test/resources/test.json"));
            assertTrue(weatherService.getCloudCover(test).equals("Mostly sunny"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
