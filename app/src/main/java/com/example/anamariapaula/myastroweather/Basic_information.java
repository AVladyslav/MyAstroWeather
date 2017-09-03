package com.example.anamariapaula.myastroweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Basic_information extends Fragment {

    private static Basic_information instance = null;

    View view = null;
    private TextView localization;
    private TextView longitude;
    private TextView latitude;
    private TextView time;
    private TextView temperature;
    private TextView pressure;
    private TextView conditions;

    public Basic_information() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Basic_information newInstance(String text, boolean isNeedToRefresh) {

        if (isNeedToRefresh || instance == null) {
            instance = new Basic_information();

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
        view =  inflater.inflate(R.layout.fragment_basic_information, container, false);
        AstroWeather astro_weather_activity = (AstroWeather) getActivity();

        getAllView(view);

        setAllText(astro_weather_activity);


        return view;
    }

    public void getAllView(View currentView) {
        localization = (TextView) currentView.findViewById(R.id.localization_bi);
        longitude = (TextView) currentView.findViewById(R.id.longitude_bi);
        latitude = (TextView) currentView.findViewById(R.id.latitude_bi);
        time = (TextView) currentView.findViewById(R.id.time_bi);
        temperature = (TextView) currentView.findViewById(R.id.temperature_bi);
        pressure = (TextView) currentView.findViewById(R.id.pressure_bi);
        conditions = (TextView) currentView.findViewById(R.id.conditions_bi);
    }

    public void setAllText(AstroWeather astro_weather_activity) {



    }
}
