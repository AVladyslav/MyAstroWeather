package com.example.anamariapaula.myastroweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class FragmentWeatherForecast extends Fragment {

    private static FragmentWeatherForecast instance = null;

    View view = null;

    TextView textView;

    ArrayList<WeatherForecast> weatherForecasts;

    public FragmentWeatherForecast() {
        // Required empty public constructor
    }

    public static FragmentWeatherForecast newInstance(String text, boolean isNeedToRefresh) {

        if (isNeedToRefresh || instance == null) {
            instance = new FragmentWeatherForecast();

            Bundle bundle = new Bundle();
            bundle.putString("msg", text);

            instance.setArguments(bundle);
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_weather_forecast, container, false);

        AstroWeather astro_weather_activity = (AstroWeather) getActivity();

        weatherForecasts = astro_weather_activity.weatherInformation.getWeatherForecast();

        getAllView(view);

        setAllText(astro_weather_activity);

        return view;
    }

    private void getAllView(View view) {
        textView = (TextView) view.findViewById(R.id.textView_weather_forecast);
    }


    public void setAllText(AstroWeather astroWeather) {
        StringBuilder sb = new StringBuilder();
        for (WeatherForecast forecast : weatherForecasts) {
            sb.append(forecast.getDay() + " ");
            sb.append(forecast.getConditionInformation() + " ");
            sb.append("from " + forecast.getLowTemperature() + " to ");
            sb.append(forecast.getHighTemperature() + " ");
            sb.append(forecast.getTemperatureUnits() + '\n');
        }
        textView.setText(sb.toString());
    }
}
