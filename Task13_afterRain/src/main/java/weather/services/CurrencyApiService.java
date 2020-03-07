package main.java.weather.services;

import main.java.weather.bo.Currency;
import main.java.weather.logger.Log;

import java.util.ArrayList;

import static io.restassured.RestAssured.get;
import static main.java.weather.services.WeatherApiService.*;

public class CurrencyApiService extends Constants {
    private static final String DYNAMIC_CURRENCY_URL = "http://www.nbrb.by/api/exrates/rates/145?ondate=%s";

    private static double daysWhenCurrencyGrowUp;
    public static double percentageCurrencyGrowUp;
    private static ArrayList<Double> rainThursdayCurrencyRateList = new ArrayList<>();
    private static ArrayList<Double> afterRainFridayCurrencyRateList = new ArrayList<>();

    public static void checkCurrencyRate() {
        Log.debug(DIVIDER) ;
        for (int i = 0; i < rainThursdayList.size(); i++) {
            Currency usdCurrenciesOnThursday = get(String.format(DYNAMIC_CURRENCY_URL, rainThursdayList.get(i))).as(Currency.class);
            Log.debug("Rate on Trursday: " + usdCurrenciesOnThursday.getOfficialRate());
            rainThursdayCurrencyRateList.add((Double) usdCurrenciesOnThursday.getOfficialRate());
            Log.debug("Date of course: " + usdCurrenciesOnThursday.getDate());
            Log.debug(DIVIDER);
        }
        for (int i = 0; i < afterRainFridayList.size(); i++) {
            Currency usdCurrenciesOnFridays = get(String.format(DYNAMIC_CURRENCY_URL, afterRainFridayList.get(i))).as(Currency.class);
            Log.debug("Rate on Friday: " + usdCurrenciesOnFridays.getOfficialRate());
            afterRainFridayCurrencyRateList.add((Double) usdCurrenciesOnFridays.getOfficialRate());
            Log.debug("Date of course: " + usdCurrenciesOnFridays.getDate());
            Log.debug(DIVIDER);
        }

        for (int i = 0; i < totalRainDays; i++) {
            if (afterRainFridayCurrencyRateList.get(i) > rainThursdayCurrencyRateList.get(i)) {
                daysWhenCurrencyGrowUp++;
            }
        }

        percentageCurrencyGrowUp = Double.valueOf(String.format("%.2f", (daysWhenCurrencyGrowUp / totalRainDays * 100)).replace(',', '.'));
        Log.debug(DIVIDER);
        Log.debug("Trursday rates size " + rainThursdayCurrencyRateList.size());
        Log.debug("Friday rates size " + afterRainFridayCurrencyRateList.size());
        Log.info("Total raining days " + totalRainDays) ;
        Log.info("Days when currency grow up " + daysWhenCurrencyGrowUp);
        Log.info(DIVIDER);
        Log.info("Percentage " + percentageCurrencyGrowUp + "%");
    }
}
