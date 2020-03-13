package main.java.weather.service;

import main.java.weather.Constants;
import main.java.weather.bo.Currency;
import main.java.weather.logger.Log;

import java.util.ArrayList;

import static io.restassured.RestAssured.get;
import static main.java.weather.service.WeatherApiService.*;

public class CurrencyApiService extends Constants {
    private static final String DYNAMIC_CURRENCY_URL = "http://www.nbrb.by/api/exrates/rates/145?ondate=%s";

    private static double daysWhenCurrencyGrowUp;
    public static double percentageCurrencyGrowUp;
    private static ArrayList<Double> thursdaysWhenItRainsCurrencyRateList = new ArrayList<>();
    private static ArrayList<Double> fridaysAfterRainyThursdayCurrencyRateList = new ArrayList<>();

    public static void checkCurrencyRate() {
        Log.debug(DIVIDER);
        for (String thursday : thursdaysWhenItRainsList) {
            Currency usdCurrenciesOnThursday = get(String.format(DYNAMIC_CURRENCY_URL, thursday)).as(Currency.class);
            Log.debug("Rate on Trursday: " + usdCurrenciesOnThursday.getOfficialRate());
            thursdaysWhenItRainsCurrencyRateList.add((Double) usdCurrenciesOnThursday.getOfficialRate());
            Log.debug("Date of course: " + usdCurrenciesOnThursday.getDate());
            Log.debug(DIVIDER);
        }
        for (String friday : fridaysAfterRainyThursdaysList) {
            Currency usdCurrenciesOnFridays = get(String.format(DYNAMIC_CURRENCY_URL, friday)).as(Currency.class);
            Log.debug("Rate on Friday: " + usdCurrenciesOnFridays.getOfficialRate());
            fridaysAfterRainyThursdayCurrencyRateList.add((Double) usdCurrenciesOnFridays.getOfficialRate());
            Log.debug("Date of course: " + usdCurrenciesOnFridays.getDate());
            Log.debug(DIVIDER);
        }

        for (int i = 0; i < totalRainDays; i++) {
            if (fridaysAfterRainyThursdayCurrencyRateList.get(i) > thursdaysWhenItRainsCurrencyRateList.get(i)) {
                daysWhenCurrencyGrowUp++;
            }
        }

        percentageCurrencyGrowUp = Double.valueOf(String.format("%.2f", (daysWhenCurrencyGrowUp / totalRainDays * 100)).replace(',', '.'));
        Log.debug(DIVIDER);
        Log.debug("Trursday rates size " + thursdaysWhenItRainsCurrencyRateList.size());
        Log.debug("Friday rates size " + fridaysAfterRainyThursdayCurrencyRateList.size());
        Log.info("Total raining days " + totalRainDays);
        Log.info("Days when currency grow up " + daysWhenCurrencyGrowUp);
        Log.info(DIVIDER);
        Log.info("Percentage " + percentageCurrencyGrowUp + "%");
    }
}
