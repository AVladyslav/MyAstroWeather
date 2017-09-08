package com.example.anamariapaula.myastroweather;

/**
 * Created by Visual on 08.09.2017.
 */

public class WeatherForecast {
    private String day;
    private int lowTemperature;
    private int highTemperature;
    private String temperatureUnits;
    private String conditionInformation;
    private int conditionCode;

    public WeatherForecast() {
    }

    public WeatherForecast(String day, int lowTemperature, int highTemperature, String temperatureUnits, String conditionInformation, int conditionCode) {
        this.day = day;
        this.lowTemperature = lowTemperature;
        this.highTemperature = highTemperature;
        this.temperatureUnits = temperatureUnits;
        this.conditionInformation = conditionInformation;
        this.conditionCode = conditionCode;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getLowTemperature() {
        return lowTemperature;
    }

    public void setLowTemperature(int lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    public int getHighTemperature() {
        return highTemperature;
    }

    public void setHighTemperature(int highTemperature) {
        this.highTemperature = highTemperature;
    }

    public String getTemperatureUnits() {
        return temperatureUnits;
    }

    public void setTemperatureUnits(String temperatureUnits) {
        this.temperatureUnits = temperatureUnits;
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
