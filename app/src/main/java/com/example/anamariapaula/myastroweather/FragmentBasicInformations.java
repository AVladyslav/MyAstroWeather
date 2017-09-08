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
    BasicWeatherInformation basicWeatherInformation;

    TextView localization_bi = null;
    TextView longitude_bi = null;
    TextView latitude_bi = null;
    TextView time_bi = null;
    TextView temperature_bi = null;
    TextView pressure_bi = null;

    ImageView imageViewWeather = null;


    View view = null;

    private static FragmentBasicInformations instance = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_basic_information, container, false);

        AstroWeather astro_weather_activity = (AstroWeather) getActivity();

        basicWeatherInformation = astro_weather_activity.weatherInformation.getBasicWeatherInformation();
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
        imageViewWeather = (ImageView) currentView.findViewById(R.id.imageViewWeather);
    }


// TODO Dokończyć time_bi!!!

    private void setAllText(AstroWeather astro_weather_activity){
        AstroDateTime current_time = astro_weather_activity.getCurrentTime();
        AstroCalculator calculator = new AstroCalculator(current_time, new AstroCalculator.Location(astro_weather_activity.latitude, astro_weather_activity.longitude));

//        time_bi.setText(getFullMoonInfo(calculator));

        localization_bi.setText(basicWeatherInformation.getPlace());
        longitude_bi.setText(Double.toString(basicWeatherInformation.getLongitude()));
        latitude_bi.setText(Double.toString(basicWeatherInformation.getLatitude()));
        temperature_bi.setText(Integer.toString(basicWeatherInformation.getTemperature()));
        pressure_bi.setText(Integer.toString(basicWeatherInformation.getPressure()) + " " + basicWeatherInformation.getPressureUnits());
//        locationDescription.setText("" + DefaultValue.weather.getActualWeather().getDescription().toString());
        imageViewWeather.setImageResource(ImageChooser.getImage(Integer.toString(basicWeatherInformation.getConditionCode())));

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
}
