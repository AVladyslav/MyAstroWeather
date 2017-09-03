package com.example.anamariapaula.myastroweather;

import android.content.Context;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ItemMethods {

    public String fetchItems(String location, AstroWeather mA, String units) {

        List<String> items = new ArrayList<>();
        String jsonString = "";

        try {
            String url = Uri.parse("https://query.yahooapis.com/v1/public/yql")
                    .buildUpon()
                    .appendQueryParameter("q", "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\""+location+"\") and u='"+units+"'")
                    .appendQueryParameter("format", "json")
                    .build().toString();
            jsonString = getUrlString(url);


            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(mA.openFileOutput(location+".txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(jsonString);
            outputStreamWriter.close();

            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody, units);

        } catch (IOException ioe) {
            //Log.e("TAG", "Failed to fetch items", ioe);
        } catch (JSONException je) {
            //Log.e("TAG", "Failed to parse JSON", je);

        }
        return jsonString;

    }

    public void parseItems(List<String> items, JSONObject jsonBody, String units)
            throws IOException, JSONException {

        JSONObject locationJson = jsonBody.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("location");
        JSONObject atmposhereJson = jsonBody.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("atmosphere");
        JSONObject conditionJson = jsonBody.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONObject("condition");
        String unitsTemp = jsonBody.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("units").getString("temperature");

        String item1 = locationJson.getString("city");
        String item2 = conditionJson.getString("temp");
        String item3 = atmposhereJson.getString("pressure");
        String item4 = atmposhereJson.getString("humidity");
        String item5 = conditionJson.getString("text");
        String item6 = conditionJson.getString("code");

        int temp = (int) (Float.parseFloat(item3));
        if(units.equals("c") || units.equals("C")) {
            temp = (int) (temp/33.8639f);
        }

        items.add(item1);
        items.add(item2);
        items.add(Integer.toString(temp));
        items.add(item4);
        items.add(item5);
        items.add(unitsTemp);
        items.add(item6);

    }
    public void parseItemsForecast(List<String> items, JSONObject jsonBody)
            throws IOException, JSONException {

        JSONArray forecastJson = jsonBody.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast");
        String unitsTemp = jsonBody.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("units").getString("temperature");

        for(int i=0; i<6; i++) {
            String item1 = forecastJson.getJSONObject(i).getString("day");//dzien
            String item2 = forecastJson.getJSONObject(i).getString("high");//temperatura
            String item3 = forecastJson.getJSONObject(i).getString("text");//warunki pogodowe

            items.add(item1);
            items.add(item2);
            items.add(item3);
        }
        items.add(unitsTemp);



    }
    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }
    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }
}
