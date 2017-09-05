package com.example.anamariapaula.myastroweather;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GettingLocationsFromYahoo extends AsyncTask<String, Void, ArrayList<Location>> {

    private JSONObject jsonMain;
    private JSONObject jsonQuery;
    private JSONObject jsonResults;
    private JSONArray jsonAllPlaces;
    private JSONObject jsonPlace;

    private ArrayList<Location> locations = new ArrayList<>();

    private static final String KEY_QUERY = "query";
    private static final String KEY_COUNT = "count";
    private static final String KEY_RESULTS = "results";
    private static final String KEY_PLACES = "place";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_ADMIN1 = "admin1";
    private static final String KEY_ADMIN2 = "admin2";
    private static final String KEY_ADMIN3 = "admin3";
    private static final String KEY_LOCALITY1 = "locality1";
    private static final String KEY_LOCALITY2 = "locality2";
    private static final String KEY_WOEID = "woeid";

    private void findLocations(String sLocation) {

        int count = 0;

        jsonMain = getJsonFromLocations(sLocation);

        try {
            jsonQuery = jsonMain.getJSONObject(KEY_QUERY);
            count = Integer.parseInt(jsonQuery.getString(KEY_COUNT));
            jsonResults = jsonQuery.getJSONObject(KEY_RESULTS);
            jsonAllPlaces = jsonResults.getJSONArray(KEY_PLACES);
        } catch (Exception e) {
            Log.e("JSON", "Can't get string from JSONObject");
        }

        for (int i=0; i<count; i++) {
            locations.add(new Location(0));
        }

        // Pobieranie danych z jsonobject do Location objects
        try {
            for (int i=0; i<count; i++) {
                locations.get(i).setCountry(getContent(KEY_COUNTRY, i));
                locations.get(i).setAdmin1(getContent(KEY_ADMIN1, i));
                locations.get(i).setAdmin2(getContent(KEY_ADMIN2, i));
                locations.get(i).setAdmin3(getContent(KEY_ADMIN3, i));
                locations.get(i).setLocality1(getContent(KEY_LOCALITY1, i));
                locations.get(i).setLocality2(getContent(KEY_LOCALITY2, i));
                locations.get(i).setWOEID(Integer.parseInt(jsonAllPlaces.getJSONObject(i).getString(KEY_WOEID)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Zakończenie pobieranych danych

        //TODO dokończyć pobieranie danych z json, jeśli nie zawiera danych o lokacji -> TOAST -> błędne dane
    }

    private String getContent(String param, int i) {
        String content = null;
        try {
            content = jsonAllPlaces.getJSONObject(i).getJSONObject(param).getString(KEY_CONTENT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return content;
    }

    private JSONObject getJsonFromLocations(String sLocation) {
        String query = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20geo.places%20where%20text%3D%22" + sLocation + "%22&format=json";
        GetJSONObject task = new GetJSONObject();
        task.execute(query);

        JSONObject json = null;

        try {
            json = task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    protected ArrayList<Location> doInBackground(String... params) {
        findLocations(params[0]);
        return locations;
    }
}
