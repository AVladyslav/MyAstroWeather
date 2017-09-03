package utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.ActualWeather;
import model.Forecast;
import model.LocationData;
import model.Unit;
import model.Weather;
import model.Wind;

/**
 * Created by Ana Maria Paula on 2017-06-21.
 */

public class JSONDataFetcher
{

    public static Weather getAllData(JSONObject json) throws JSONException, ParseException {

        return new Weather
        (
            getLocationData(json),
            getActualWeatherData(json),
            getWindData(json),
            getForecastData(json),
            getUnitData(json)
        );
    }

    public static ActualWeather getActualWeatherData(JSONObject jsonObj) throws JSONException, ParseException {

        JSONObject json = selectSpecialData(jsonObj);
        String temperature = json.getJSONObject("item").getJSONObject("condition").getString("temp");
        String pressure = json.getJSONObject("atmosphere").getString("pressure");
        String description = json.getJSONObject("item").getJSONObject("condition").getString("text");
        String imageCode = json.getJSONObject("item").getJSONObject("condition").getString("code");

        return new ActualWeather(temperature, pressure, description, imageCode);
    }

    public static Wind getWindData(JSONObject jsonObj) throws JSONException, ParseException {

        JSONObject json = selectSpecialData(jsonObj);
        String windForce = json.getJSONObject("wind").getString("speed");
        String windDirection = json.getJSONObject("wind").getString("direction");
        String humidity = json.getJSONObject("atmosphere").getString("humidity");
        String visibility = json.getJSONObject("atmosphere").getString("visibility");

        return new Wind(windForce, windDirection, humidity, visibility);
    }

    public static List<Forecast> getForecastData(JSONObject jsonObj) throws JSONException, ParseException {

        JSONObject json = selectSpecialData(jsonObj);
        JSONArray array = json.getJSONObject("item").getJSONArray("forecast");
        List<Forecast> futherWeathers = new ArrayList<>(7);

        for (int index = 1; index <= 7; index++) {

            JSONObject row = array.getJSONObject(index);
            String minTemperature = row.getString("low");
            String maxTemperature = row.getString("high");
            String day = row.getString("day");
            String imageCode = row.getString("code");

            futherWeathers.add(new Forecast(minTemperature, maxTemperature, day, imageCode));
        }

        return futherWeathers;
    }

    public static LocationData getLocationData(JSONObject jsonObj) throws JSONException, ParseException {

        JSONObject json = selectSpecialData(jsonObj);
        String country = json.getJSONObject("location").getString("country");
        String city = json.getJSONObject("location").getString("city");
        String longitude = json.getJSONObject("item").getString("long");
        String latitude = json.getJSONObject("item").getString("lat");
        Date updateDate = new Date();

        return new LocationData(country, city, longitude, latitude, updateDate);
    }

    public static Unit getUnitData(JSONObject jsonObj) throws JSONException, ParseException {

        JSONObject json = selectSpecialData(jsonObj);
        String pressure = json.getJSONObject("units").getString("pressure");
        String speed = json.getJSONObject("units").getString("speed");
        String temperature = "°" + json.getJSONObject("units").getString("temperature");
        String distance = json.getJSONObject("units").getString("distance");

        return new Unit(pressure, speed, temperature, distance);
    }

    public static JSONObject getJSON(String url) throws JSONException {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer content = new StringBuffer();
            String line = "";

            while ((line = buffer.readLine()) != null) {
                content.append(line);
            }

            inputStream.close();
            connection.disconnect();
            return new JSONObject(content.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject selectSpecialData(JSONObject weatherJSON) throws JSONException, ParseException {
        return weatherJSON.getJSONObject("query").getJSONObject("results").getJSONObject("channel");
    }
}
