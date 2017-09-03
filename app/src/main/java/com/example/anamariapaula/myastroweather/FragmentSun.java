package com.example.anamariapaula.myastroweather;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.*;

import java.util.Calendar;

/**
 * Created by Ana Maria Paula on 27.05.2017.
 */

public class FragmentSun extends Fragment {

    View view;
    private TextView sunrise_time;
    private TextView sunset_time;
    private TextView civil_dawn;
    private TextView civil_dusk;
    private TextView sunrise_azimuth;
    private TextView sunset_azimuth;

    private static FragmentSun instance = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sun, container, false);
        AstroWeather astro_weather_activity = (AstroWeather) getActivity();

        getAllView(view);

        setAllText(astro_weather_activity);
        return view;
    }

    private void getAllView(View currentView) {
        sunrise_time = (TextView) currentView.findViewById(R.id.sunrise_time);
        sunset_time = (TextView) currentView.findViewById(R.id.sunset_time);
        civil_dawn = (TextView) currentView.findViewById(R.id.civil_dawn);
        civil_dusk = (TextView) currentView.findViewById(R.id.civil_dusk);
        sunrise_azimuth = (TextView) currentView.findViewById(R.id.sunrise_azimuth);
        sunset_azimuth = (TextView) currentView.findViewById(R.id.sunset_azimuth);
    }

    private void setAllText(AstroWeather astro_weather_activity){
        AstroDateTime current_time = astro_weather_activity.getCurrentTime();
        AstroCalculator calculator = new AstroCalculator(current_time, new AstroCalculator.Location(astro_weather_activity.latitude, astro_weather_activity.longitude));

        sunrise_time.setText(getSunriseInfo(calculator));
        sunset_time.setText(getSunsetInfo(calculator));
        civil_dawn.setText(getCivildawnInfo(calculator));
        civil_dusk.setText(getCivilduskInfo(calculator));
        sunrise_azimuth.setText(getSunriseAzimuthInfo(calculator));
        sunset_azimuth.setText(getSunsetAzimuthInfo(calculator));
    }

    public static FragmentSun newInstance(String text, boolean isNeedToRefresh) {
        if (isNeedToRefresh || instance == null) {
            instance = new FragmentSun();

            Bundle bundle = new Bundle();
            bundle.putString("msg", text);

            instance.setArguments(bundle);
        }
        return instance;
    }

    private String getSunriseInfo(AstroCalculator calculator) {
        String info;
        AstroDateTime sunrise_t = calculator.getSunInfo().getSunrise();
        info = Integer.toString(sunrise_t.getHour()) + ':' + Integer.toString(sunrise_t.getMinute()) + ':' + Integer.toString(sunrise_t.getSecond());
        return info;
    }

    private String getSunsetInfo(AstroCalculator calculator) {
        String info;
        AstroDateTime sunset_t = calculator.getSunInfo().getSunset();
        info = Integer.toString(sunset_t.getHour()) + ':' + Integer.toString(sunset_t.getMinute()) + ':' + Integer.toString(sunset_t.getSecond());
        return info;
    }

    private String getCivildawnInfo(AstroCalculator calculator) {
        String info;
        AstroDateTime civildawn_t = calculator.getSunInfo().getTwilightEvening();
        info = Integer.toString(civildawn_t.getHour()) + ':' + Integer.toString(civildawn_t.getMinute()) + ':' + Integer.toString(civildawn_t.getSecond());
        return info;
    }

    private String getCivilduskInfo(AstroCalculator calculator) {
        String info;
        AstroDateTime civildusk_t = calculator.getSunInfo().getTwilightMorning();
        info = Integer.toString(civildusk_t.getHour()) + ':' + Integer.toString(civildusk_t.getMinute()) + ':' + Integer.toString(civildusk_t.getSecond());
        return info;
    }

    private String getSunriseAzimuthInfo(AstroCalculator calculator) {
        String info;
        double sunrise_azimuth_t = calculator.getSunInfo().getAzimuthRise();
        info = String.format("%.2f", sunrise_azimuth_t);
        return info;
    }

    private String getSunsetAzimuthInfo(AstroCalculator calculator) {
        String info;
        double sunset_azimuth_t = calculator.getSunInfo().getAzimuthSet();
        info = String.format("%.2f", sunset_azimuth_t);
        return info;
    }
}
