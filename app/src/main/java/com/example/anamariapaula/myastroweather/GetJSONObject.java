package com.example.anamariapaula.myastroweather;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class GetJSONObject extends AsyncTask<String, Void, JSONObject> {
    private JSONObject getJSONfromURL(String urlString){
        URL url;
        InputStream inputStream;
        String result = "";
        JSONObject jArray = null;
        URLConnection con = null;

        // Download JSON data from URL
        try{
            url = new URL(urlString);
            con = url.openConnection();
            con.setDoInput(true);
            con.connect();
        }catch(Exception e){
            Log.e("log_tag", "Error in http connection "+e.toString());
        }

        // Convert response to string
        try{
            inputStream = con.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            inputStream.close();
            result=sb.toString();
        }catch(Exception e){
            Log.e("log_tag", "Error converting result "+e.toString());
        }

        try{

            jArray = new JSONObject(result);
        }catch(JSONException e){
            Log.e("log_tag", "Error parsing data "+e.toString());
        }

        return jArray;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        return getJSONfromURL(params[0]);
    }
}
