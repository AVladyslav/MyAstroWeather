package com.example.anamariapaula.myastroweather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import utility.DefaultValue;

public class FragmentBasicInformations extends Fragment {
    TextView localization_bi = null;
    TextView longitude_bi = null;
    TextView latitude_bi = null;
    TextView time_bi = null;
    TextView temperature_bi = null;
    TextView pressure_bi = null;

    ImageView imageViewWeather = null;


    View view = null;

    private static FragmentBasicInformations instance = null;

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
        localization_bi = (TextView) currentView.findViewById(R.id.localization_bi);
        longitude_bi = (TextView) currentView.findViewById(R.id.longitude_bi);
        latitude_bi = (TextView) currentView.findViewById(R.id.latitude_bi);
        time_bi = (TextView) currentView.findViewById(R.id.time_bi);
        temperature_bi = (TextView) currentView.findViewById(R.id.temperature_bi);
        pressure_bi = (TextView) currentView.findViewById(R.id.pressure_bi);
    }


// TODO Dokończyć time_bi!!!

    private void setAllText(AstroWeather astro_weather_activity){
        AstroDateTime current_time = astro_weather_activity.getCurrentTime();
        AstroCalculator calculator = new AstroCalculator(current_time, new AstroCalculator.Location(astro_weather_activity.latitude, astro_weather_activity.longitude));

//        time_bi.setText(getFullMoonInfo(calculator));

        localization_bi.setText("" + DefaultValue.weather.getLocationData().getCity().toString() + ", " + DefaultValue.weather.getLocationData().getCountry().toString());
        longitude_bi.setText("" + DefaultValue.weather.getLocationData().getLongitude().toString());
        latitude_bi.setText("" + DefaultValue.weather.getLocationData().getLatitude().toString());
        temperature_bi.setText("" + DefaultValue.weather.getActualWeather().getTemperature().toString() + DefaultValue.weather.getUnit().getTemperature().toString());
        pressure_bi.setText("" + DefaultValue.weather.getActualWeather().getPressure().toString() + DefaultValue.weather.getUnit().getPressure().toString());
//        locationDescription.setText("" + DefaultValue.weather.getActualWeather().getDescription().toString());
        imageViewWeather.setImageResource(ImageChooser.getImage(DefaultValue.weather.getActualWeather().getImageCode().toString()));

    }

    public static FragmentBasicInformations newInstance(String text, boolean isNeedToRefresh) {

        if (isNeedToRefresh || instance == null) {
            instance = new FragmentBasicInformations();

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
