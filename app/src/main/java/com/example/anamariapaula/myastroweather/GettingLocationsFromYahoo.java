package com.example.anamariapaula.myastroweather;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Visual on 05.09.2017.
 */

public class GettingLocationsFromYahoo {

    JSONObject json;
    ArrayList<Location> locations;

    public ArrayList<Location> findLocations(String sLocation) {

        int count = 0;

        json = getJsonFromLocations(sLocation);

        try {
            count = Integer.parseInt(json.getString("count"));
        } catch (Exception e) {
            Log.e("JSON", "Can't get string from JSONObject");
        }

        for (int i=0; i<count; i++) {
            locations.add(new Location());
        }

        // Pobieranie danych z jsonobject do Location objects
        try {
            for (int i=0; i<count; i++) {
                locations.get(i).setCountry(json.getString("country"));
            }
            for (int i=0; i<count; i++) {
                locations.get(i).setAdmin1(json.getString("admin1"));
            }
            for (int i=0; i<count; i++) {
                locations.get(i).setAdmin2(json.getString("admin2"));
            }
            for (int i=0; i<count; i++) {
                locations.get(i).setAdmin3(json.getString("admin3"));
            }
            for (int i=0; i<count; i++) {
                locations.get(i).setLocality1(json.getString("locality1"));
            }
            for (int i=0; i<count; i++) {
                locations.get(i).setLocality2(json.getString("locality2"));
            }
            for (int i=0; i<count; i++) {
                locations.get(i).setWOEID(Integer.parseInt(json.getString("woeid")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Zakończenie pobieranych danych

        //TODO dokończyć pobieranie danych z json, jeśli nie zawiera danych o lokacji -> TOAST -> błędne dane

        return locations;
    }

    private JSONObject getJsonFromLocations(String sLocation) {
        String query = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20geo.places%20where%20text%3D%22" + sLocation + "%22&format=json";
        return GetJSONObject.getJSONfromURL(query);
    }
}
