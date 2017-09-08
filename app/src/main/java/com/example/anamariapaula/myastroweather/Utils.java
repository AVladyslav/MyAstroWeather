package com.example.anamariapaula.myastroweather;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    private static String EXTENSION = ".json";

    public static boolean isOnline(Context context)
    {
        boolean isOnline;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isOnline = networkInfo != null && networkInfo.isConnectedOrConnecting();
        return isOnline;
    }

    public static void noInternetConnectionInfo(final Context context)
    {
        new AlertDialog.Builder(context)
                .setTitle("Offline")
                .setMessage("No available internet connection!")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Can not update data.", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    public static JSONObject getJsonFromWOEID(int woeid, boolean isMetricsUnits) {
        String query;
        if (isMetricsUnits) {
            //TODO check this
            query = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid=" + woeid + "%20and%20u=%22c%22&format=json";
        } else {
            query = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid=" + woeid + "%20&format=json";
        }

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

    public static void writeJSONObjectToFile(final Context context, JSONObject jsonObject, int filename) {
        Writer output;
        File file = new File(context.getFilesDir()+ "/" + Integer.toString(filename) + EXTENSION);
        try {
            output = new BufferedWriter(new FileWriter(file));
            output.write(jsonObject.toString());
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isDataFileExists(String filename) {
        File f = new File(filename + EXTENSION);
        return f.canRead();
    }

    public static boolean isNeedToBeRefreshed(Context context, String filename) {
        boolean isNeed = true;
        String info = readInfoFromFile(context, filename);
        try {
            JSONObject query = new JSONObject(info).getJSONObject("query");
            JSONObject results = query.getJSONObject("results");
            JSONObject channel = results.getJSONObject("channel");
            String lastBuildDate = channel.getString("lastBuildDate");
            String ttl = channel.getString("ttl");
            int minutes = Integer.parseInt(ttl);
            Calendar c = Calendar.getInstance();
            Date currentTime = c.getTime();

            Date date = new Date(lastBuildDate);
            c.setTime(date);
            c.add(Calendar.MINUTE, minutes);
            if (c.getTime().before((currentTime))) {
                isNeed = true;
            } else {
                isNeed = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return isNeed;
    }

    private static JSONObject getJSONObjectFromString(String string) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(string);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private static String readInfoFromFile(Context context, String filename) {
        String info = "";
        try {
            File file = new File(context.getFilesDir(), filename + EXTENSION);
            boolean isExists = file.exists();
            FileReader reader = new FileReader(file);
            char[] buffer = new char[5000];
            int length = reader.read(buffer);
            reader.close();
            info = new String(buffer, 0, length);
        } catch (IOException e) {
            Toast.makeText(context, "Error while reading file.", Toast.LENGTH_SHORT).show();
        }
        return info;
    }

    public static void readAllData() {
    }

    public static JSONObject getJsonFromFile(final Context context, int woeid) {
        return getJSONObjectFromString(readInfoFromFile(context, Integer.toString(woeid)));
    //TODO dokończyć
    }
}
