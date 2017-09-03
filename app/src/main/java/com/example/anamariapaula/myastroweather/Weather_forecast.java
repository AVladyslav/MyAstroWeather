package com.example.anamariapaula.myastroweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Weather_forecast extends Fragment {

    private static Weather_forecast instance = null;

    View view = null;

    public Weather_forecast() {
        // Required empty public constructor
    }

    public static Weather_forecast newInstance(String text, boolean isNeedToRefresh) {

        if (isNeedToRefresh || instance == null) {
            instance = new Weather_forecast();

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

        //getAllView(view);

        //setAllText(astro_weather_activity);

        return view;
    }
}
