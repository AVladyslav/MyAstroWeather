package com.example.anamariapaula.myastroweather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

/**
 * Created by Ana Maria Paula on 27.05.2017.
 */

public class FragmentMoon extends Fragment {

    TextView moonrise = null;
    TextView moonset = null;
    TextView new_moon = null;
    TextView full_moon = null;
    TextView moon_phase = null;
    TextView synodic_period = null;


    View view = null;

    private static FragmentMoon instance = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_moon, container, false);

        AstroWeather astro_weather_activity = (AstroWeather) getActivity();

        getAllView(view);

        setAllText(astro_weather_activity);

        return view;
    }

    private void getAllView(View currentView) {
        moonrise = (TextView) currentView.findViewById(R.id.moonrise);
        moonset = (TextView) currentView.findViewById(R.id.moonset);
        new_moon = (TextView) currentView.findViewById(R.id.new_moon);
        full_moon = (TextView) currentView.findViewById(R.id.full_moon);
        moon_phase = (TextView) currentView.findViewById(R.id.moon_phase);
        synodic_period = (TextView) currentView.findViewById(R.id.synodic_period);
    }

    private void setAllText(AstroWeather astro_weather_activity){
        AstroDateTime current_time = astro_weather_activity.getCurrentTime();
        AstroCalculator calculator = new AstroCalculator(current_time, new AstroCalculator.Location(astro_weather_activity.latitude, astro_weather_activity.longitude));

        moonrise.setText(getMoonriseInfo(calculator));
        moonset.setText(getMoonsetInfo(calculator));
        new_moon.setText(getNewMoonInfo(calculator));
        full_moon.setText(getFullMoonInfo(calculator));
        moon_phase.setText(getMoonPhaseInfo(calculator));
        synodic_period.setText(getSynodicPeriodInfo(calculator));
    }

    public static FragmentMoon newInstance(String text, boolean isNeedToRefresh) {

        if (isNeedToRefresh || instance == null) {
            instance = new FragmentMoon();

            Bundle bundle = new Bundle();
            bundle.putString("msg", text);

            instance.setArguments(bundle);
        }
        return instance;
    }

    private String getMoonriseInfo(AstroCalculator calculator) {
        String info;
        AstroDateTime moonrise_t = calculator.getMoonInfo().getMoonrise();
        info = Integer.toString(moonrise_t.getHour()) + ':' + Integer.toString(moonrise_t.getMinute()) + ':' + Integer.toString(moonrise_t.getSecond());
        return info;
    }

    private String getMoonsetInfo(AstroCalculator calculator) {
        String info;
        AstroDateTime moonset_t = calculator.getMoonInfo().getMoonset();
        info = Integer.toString(moonset_t.getHour()) + ':' + Integer.toString(moonset_t.getMinute()) + ':' + Integer.toString(moonset_t.getSecond());
        return info;
    }

    private String getNewMoonInfo(AstroCalculator calculator) {
        String info;
        AstroDateTime new_moon_t = calculator.getMoonInfo().getNextNewMoon();
        info = Integer.toString(new_moon_t.getDay()) + '-' + Integer.toString(new_moon_t.getMonth()) + '-' + Integer.toString(new_moon_t.getYear());
        return info;
    }

    private String getFullMoonInfo(AstroCalculator calculator) {
        String info;
        AstroDateTime full_moon_t = calculator.getMoonInfo().getNextFullMoon();
        info = Integer.toString(full_moon_t.getDay()) + '-' + Integer.toString(full_moon_t.getMonth()) + '-' + Integer.toString(full_moon_t.getYear());
        return info;
    }

    private String getMoonPhaseInfo(AstroCalculator calculator) {
        String info;
        double moon_phase_t = calculator.getMoonInfo().getIllumination() * 100;
        info = String.format("%.2f%%", moon_phase_t);
        return info;
    }

    private String getSynodicPeriodInfo(AstroCalculator calculator) {
        String info;
        double synodic_period_t = calculator.getMoonInfo().getAge();
        info = String.format("%.0f", synodic_period_t);
        return info;
    }
}
