package test.java.weather;

import static main.java.weather.service.CurrencyApiService.checkCurrencyRate;
import static main.java.weather.service.CurrencyApiService.percentageCurrencyGrowUp;

import main.java.weather.service.WeatherApiService;
import main.java.weather.util.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;

@Listeners({TestListener.class})

public class AllDaysTogetherTest {

    @Test
    public void checkWeather() throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        WeatherApiService weatherApiService = new WeatherApiService();
        weatherApiService
                .findAllThursdays()
                .findAllRainThursday()
                .findAllFridaysAfterRain();
        checkCurrencyRate();
        Assert.assertTrue(percentageCurrencyGrowUp > 50, "Coincidence percentage less than 50%");
    }
}
