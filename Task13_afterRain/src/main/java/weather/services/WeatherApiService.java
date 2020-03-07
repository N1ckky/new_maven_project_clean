package main.java.weather.services;

import main.java.weather.logger.Log;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class WeatherApiService extends Constants {
    private static final String URL_PATTERN = "http://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=6ec82649e7b941fc8d6214524202502&q=Homyel&format=xml&date=%s";
    public static final String YEAR_2017_URL = "http://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=6ec82649e7b941fc8d6214524202502&q=Homyel&format=xml&date=2017-01-01&enddate=2018-01-01";
    private static final String XPATH_PATTERN = "//mintempC[not(contains(.,'-'))]//ancestor::weather//precipMM[not(contains(.,'0.0'))]//ancestor::weather//date";

    private static ArrayList<String> thursdayList = new ArrayList();
    private static ArrayList<String> fridayList = new ArrayList();
    static ArrayList<String> rainThursdayList = new ArrayList();
    static ArrayList<String> afterRainFridayList = new ArrayList();

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private static Document doc;
    private XPath xPath;
    private XPathFactory xPathFactory;
    static double totalRainDays;

    public WeatherApiService findAllThursdays() {
        Calendar calendar = new GregorianCalendar(2017, Calendar.JANUARY, 5);
        while (calendar.get(Calendar.YEAR) < 2018) {
            thursdayList.add(DATE_FORMAT.format(calendar.getTime()));
            Log.debug("Thursday: " + DATE_FORMAT.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            fridayList.add(DATE_FORMAT.format(calendar.getTime()));
            Log.debug("Friday: " + DATE_FORMAT.format(calendar.getTime()));
            Log.debug(DIVIDER);
            calendar.add(Calendar.DAY_OF_MONTH, 6);
        }
        Log.info("Total rain days in 2017: " + thursdayList.size());
        return this;
    }

    public WeatherApiService findAllRainThursday() throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        for (String date : thursdayList) {
            buildDom(date);
            XPathExpression expression = xPath.compile(XPATH_PATTERN);
            Object result = expression.evaluate(doc, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;
            int rainThursdayListSize = rainThursdayList.size();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Log.debug(nodeList.item(i).getChildNodes().item(0).getTextContent());
                rainThursdayList.add(nodeList.item(i).getChildNodes().item(0).getTextContent());
            }
            if (rainThursdayListSize < rainThursdayList.size()) {
                Log.debug("Rain list size: " + rainThursdayList.size());
                Log.debug(DIVIDER);
            }
        }
        totalRainDays = rainThursdayList.size();
        Log.info("Total rain Thursdays: " + totalRainDays);
        return this;
    }

    public WeatherApiService findAllFridaysAfterRain() {
        for (int i = 0; i < thursdayList.size(); i++) {
            for (int j = 0; j < rainThursdayList.size(); j++) {
                if (thursdayList.get(i).equals(rainThursdayList.get(j))) {
                    afterRainFridayList.add(fridayList.get(i));
                    Log.debug("Rain Thursday List: " + rainThursdayList.get(j));
                    Log.debug("Rain Friday List: " + afterRainFridayList.get(j));
                    Log.debug(DIVIDER);
                }
            }
        }
        Log.info(DIVIDER);
        Log.info("Rain Thursday size: " + rainThursdayList.size());
        Log.info("Rain Friday size: " + afterRainFridayList.size());
        return this;
    }

    private void buildDom(String date) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream response = given().when().get(String.format(URL_PATTERN, date)).asInputStream();
        doc = builder.parse(response);
        xPathFactory = XPathFactory.newInstance();
        xPath = xPathFactory.newXPath();
    }
}
