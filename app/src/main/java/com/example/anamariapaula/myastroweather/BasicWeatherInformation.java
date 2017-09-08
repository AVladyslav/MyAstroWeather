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
    String conditionInformation;
    int conditionCode;

    public BasicWeatherInformation(double longitude, double latitude, int temperature, String temperatureUnits, int pressure, String pressureUnits, String conditionInformation, String pictureWeatherCurrentInformation, int conditionCode) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.temperature = temperature;
        this.temperatureUnits = temperatureUnits;
        this.pressure = pressure;
        this.pressureUnits = pressureUnits;
        this.conditionInformation = conditionInformation;
        this.conditionCode = conditionCode;
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

    public String getConditionInformation() {
        return conditionInformation;
    }

    public void setConditionInformation(String conditionInformation) {
        this.conditionInformation = conditionInformation;
    }

    public int getConditionCode() {
        return conditionCode;
    }

    public void setConditionCode(int conditionCode) {
        this.conditionCode = conditionCode;
    }
}
