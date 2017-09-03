package com.example.anamariapaula.myastroweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Additional_information extends Fragment {

    private static Additional_information instance = null;


    View view = null;

    TextView wind;
    TextView direction;
    TextView humildity;
    TextView visibility;

    public Additional_information() {
        // Required empty public constructor
    }


    public static Additional_information newInstance(String text, boolean isNeedToRefresh) {
        if (isNeedToRefresh || instance == null) {
            instance = new Additional_information();

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
        view =  inflater.inflate(R.layout.fragment_additional_information, container, false);

        AstroWeather astro_weather_activity = (AstroWeather) getActivity();

        //getAllView(view);

        //setAllText(astro_weather_activity);

        return view;
    }

    public void getAllView(View currentView) {

        wind = (TextView) currentView.findViewById(R.id.wind_ai);
        direction = (TextView) currentView.findViewById(R.id.wind_direction_ai);
        humildity = (TextView) currentView.findViewById(R.id.humildity_ai);
        visibility = (TextView) currentView.findViewById(R.id.visibility_ai);
    }

}
