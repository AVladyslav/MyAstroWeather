package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Szaman on 2017-06-21.
 */

public class Weather implements Serializable
{

    private List<Forecast> forecast = new ArrayList<>(7);
    private LocationData locationData = null;
    private ActualWeather actualWeather = null;
    private Wind wind = null;
    private Unit unit = null;

    public Weather(LocationData locationData, ActualWeather actualWeather, Wind wind, List<Forecast> forecast, Unit unit)
    {
        this.locationData = locationData;
        this.actualWeather = actualWeather;
        this.wind = wind;
        this.forecast = forecast;
        this.unit = unit;
    }

    public LocationData getLocationData() {
        return locationData;
    }

    public void setLocationData(LocationData locationData) {
        this.locationData = locationData;
    }

    public ActualWeather getActualWeather() {
        return actualWeather;
    }

    public void setActualWeather(ActualWeather actualWeather) {
        this.actualWeather = actualWeather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}