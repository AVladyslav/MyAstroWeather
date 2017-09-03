package data;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

import model.Weather;
import utility.DefaultValue;
import utility.JSONDataFetcher;

/**
 * Created by Ana Maria Paula on 2017-06-21.
 */

public class FetchWeatherData extends AsyncTask<String, Void, Weather>
{
    @Override
    protected Weather doInBackground(String... params)
    {
        Weather weather = null;
        String unit = params[0];
        String country = params[1];
        String city = params[2];

        try
        {
            JSONObject json = JSONDataFetcher.getJSON(
                            DefaultValue.WEATHER_URL[0] + unit +        //jednostki
                            DefaultValue.WEATHER_URL[1] + city +        //miasto
                            DefaultValue.WEATHER_URL[2] + country +     //kraj
                            DefaultValue.WEATHER_URL[3]);

            weather = JSONDataFetcher.getAllData(json);
        }
        catch (JSONException | ParseException e)
        {
            e.printStackTrace();
        }

        return weather;
    }

}
