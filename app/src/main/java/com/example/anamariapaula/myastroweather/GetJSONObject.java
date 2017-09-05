package com.example.anamariapaula.myastroweather;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by Visual on 05.09.2017.
 */

public class GetJSONObject {
    public static JSONObject getJSONfromURL(String urlString){
        URL url;
        InputStream is = null;
        String result = "";
        JSONObject jArray = null;
        URLConnection con;

        // Download JSON data from URL
        try{
            url = new URL(urlString);
            con = url.openConnection();
        }catch(Exception e){
            Log.e("log_tag", "Error in http connection "+e.toString());
        }

        // Convert response to string
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            //reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
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
}
