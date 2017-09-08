package com.example.anamariapaula.myastroweather;

import java.util.ArrayList;

/**
 * Created by Visual on 08.09.2017.
 */

public class WeatherInformation {
    private BasicWeatherInformation basicWeatherInformation;
    private AdditionalWeatherInformation additionalWeatherInformation;
    private ArrayList<WeatherForecast> weatherForecast;

    public WeatherInformation() {
    }

    public WeatherInformation(BasicWeatherInformation basicWeatherInformation, AdditionalWeatherInformation additionalWeatherInformation, ArrayList<WeatherForecast> weatherForecast) {
        this.basicWeatherInformation = basicWeatherInformation;
        this.additionalWeatherInformation = additionalWeatherInformation;
        this.weatherForecast = weatherForecast;
    }

    public BasicWeatherInformation getBasicWeatherInformation() {
        return basicWeatherInformation;
    }

    public void setBasicWeatherInformation(BasicWeatherInformation basicWeatherInformation) {
        this.basicWeatherInformation = basicWeatherInformation;
    }

    public AdditionalWeatherInformation getAdditionalWeatherInformation() {
        return additionalWeatherInformation;
    }

    public void setAdditionalWeatherInformation(AdditionalWeatherInformation additionalWeatherInformation) {
        this.additionalWeatherInformation = additionalWeatherInformation;
    }

    public ArrayList<WeatherForecast> getWeatherForecast() {
        return weatherForecast;
    }

    public void setWeatherForecast(ArrayList<WeatherForecast> weatherForecast) {
        this.weatherForecast = weatherForecast;
    }
}
