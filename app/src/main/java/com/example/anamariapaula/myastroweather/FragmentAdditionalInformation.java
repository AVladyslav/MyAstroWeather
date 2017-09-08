package com.example.anamariapaula.myastroweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;


public class FragmentAdditionalInformation extends Fragment {

    private static FragmentAdditionalInformation instance = null;


    View view = null;

    TextView wind;
    TextView direction;
    TextView humildity;
    TextView visibility;

    AdditionalWeatherInformation additionalWeatherInformation;

    public FragmentAdditionalInformation() {
        // Required empty public constructor
    }


    public static FragmentAdditionalInformation newInstance(String text, boolean isNeedToRefresh) {
        if (isNeedToRefresh || instance == null) {
            instance = new FragmentAdditionalInformation();

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

        additionalWeatherInformation = astro_weather_activity.weatherInformation.getAdditionalWeatherInformation();
        getAllView(view);

        setAllText(astro_weather_activity);

        return view;
    }

    public void getAllView(View currentView) {

        wind = (TextView) currentView.findViewById(R.id.wind_ai);
        direction = (TextView) currentView.findViewById(R.id.wind_direction_ai);
        humildity = (TextView) currentView.findViewById(R.id.humildity_ai);
        visibility = (TextView) currentView.findViewById(R.id.visibility_ai);
    }


    private void setAllText(AstroWeather astro_weather_activity){
        AstroDateTime current_time = astro_weather_activity.getCurrentTime();
        AstroCalculator calculator = new AstroCalculator(current_time, new AstroCalculator.Location(astro_weather_activity.latitude, astro_weather_activity.longitude));

//        time_bi.setText(getFullMoonInfo(calculator));

        wind.setText(additionalWeatherInformation.getSpeedOfWind() + " " + additionalWeatherInformation.getSpeedUnits());
        direction.setText(Integer.toString(additionalWeatherInformation.getWindDirection()));
        humildity.setText(Integer.toString(additionalWeatherInformation.getHumidity()));
        visibility.setText(Double.toString(additionalWeatherInformation.getVisibility()));
//        locationDescription.setText("" + DefaultValue.weather.getActualWeather().getDescription().toString());
        //imageViewWeather.setImageResource(ImageChooser.getImage(DefaultValue.weather.getActualWeather().getImageCode().toString()));

    }
}
