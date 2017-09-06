package com.example.anamariapaula.myastroweather;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Visual on 07.09.2017.
 */

public class LocationGetter extends AsyncTask<String, Void, Location> {
    @Override
    protected Location doInBackground(String... params) {
        return getLocation(params[0], params[1]);
    }

    private Location getLocation(String lat, String lon) {
        Location location = null;
        JSONObject jsonObject = getJsonFromGeo(lat, lon);
        int woeid = getWOEIDFromJson(jsonObject);
        if (woeid != -1) {
            location = new Location();
            location.setWOEID(woeid);
        }

        return location;
    }

    private JSONObject getJsonFromGeo(String lat, String lon) {
        String query = "https://query.yahooapis.com/v1/public/yql?q=SELECT%20woeid%20FROM%20geo.places%20WHERE%20woeid%20in%20(SELECT%20woeid%20FROM%20geo.places%20WHERE%20text%3D%22(" + lat + "%20%2C" + lon + ")%22)%20&format=json&diagnostics=true&callback=";
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

    private int getWOEIDFromJson(JSONObject json) {
        int out = -1;

        JSONObject jsonResults = null;
        try {
            jsonResults = json.getJSONObject("results");
            //TODO źle sę dzieje
            JSONObject place = jsonResults.getJSONObject("place");
            out = Integer.parseInt(place.getString("woeid"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return out;
    }
}
