package com.example.anamariapaula.myastroweather;

/**
 * Created by Visual on 08.09.2017.
 */

public class BasicWeatherInformation {
    double longitude;
    double latitude;
    int temperature;
    String temperatureUnits;
    int pressure;
    String pressureUnits;
    String currentWeatherInformation;
    String pictureWeatherCurrentInformation;

    public BasicWeatherInformation(double longitude, double latitude, int temperature, String temperatureUnits, int pressure, String pressureUnits, String currentWeatherInformation, String pictureWeatherCurrentInformation) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.temperature = temperature;
        this.temperatureUnits = temperatureUnits;
        this.pressure = pressure;
        this.pressureUnits = pressureUnits;
        this.currentWeatherInformation = currentWeatherInformation;
        this.pictureWeatherCurrentInformation = pictureWeatherCurrentInformation;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getTemperatureUnits() {
        return temperatureUnits;
    }

    public void setTemperatureUnits(String temperatureUnits) {
        this.temperatureUnits = temperatureUnits;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public String getPressureUnits() {
        return pressureUnits;
    }

    public void setPressureUnits(String pressureUnits) {
        this.pressureUnits = pressureUnits;
    }

    public String getCurrentWeatherInformation() {
        return currentWeatherInformation;
    }

    public void setCurrentWeatherInformation(String currentWeatherInformation) {
        this.currentWeatherInformation = currentWeatherInformation;
    }

    public String getPictureWeatherCurrentInformation() {
        return pictureWeatherCurrentInformation;
    }

    public void setPictureWeatherCurrentInformation(String pictureWeatherCurrentInformation) {
        this.pictureWeatherCurrentInformation = pictureWeatherCurrentInformation;
    }

    //TODO dokończyć
}
