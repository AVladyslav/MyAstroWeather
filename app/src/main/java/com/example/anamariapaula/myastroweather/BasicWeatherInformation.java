package com.example.anamariapaula.myastroweather;

/**
 * Created by Visual on 08.09.2017.
 */

public class BasicWeatherInformation {
    private String place;
    private double longitude;
    private double latitude;
    private int temperature;
    private String temperatureUnits;
    private int pressure;
    private String pressureUnits;
    private String conditionInformation;
    private int conditionCode;

    public BasicWeatherInformation() {
    }

    public BasicWeatherInformation(String place, double longitude, double latitude, int temperature, String temperatureUnits, int pressure, String pressureUnits, String conditionInformation, String pictureWeatherCurrentInformation, int conditionCode) {
        this.place = place;
        this.longitude = longitude;
        this.latitude = latitude;
        this.temperature = temperature;
        this.temperatureUnits = temperatureUnits;
        this.pressure = pressure;
        this.pressureUnits = pressureUnits;
        this.conditionInformation = conditionInformation;
        this.conditionCode = conditionCode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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
