package com.example.anamariapaula.myastroweather;

/**
 * Created by Visual on 08.09.2017.
 */

public class AdditionalWeatherInformation {

    int speedOfWind;
    String speedUnits;
    int windDirection;
    int humidity;
    float visibility;

    public AdditionalWeatherInformation(int speedOfWind, String speedUnits, int windDirection, int humidity, float visibility) {
        this.speedOfWind = speedOfWind;
        this.speedUnits = speedUnits;
        this.windDirection = windDirection;
        this.humidity = humidity;
        this.visibility = visibility;
    }

    public int getSpeedOfWind() {
        return speedOfWind;
    }

    public void setSpeedOfWind(int speedOfWind) {
        this.speedOfWind = speedOfWind;
    }

    public String getSpeedUnits() {
        return speedUnits;
    }

    public void setSpeedUnits(String speedUnits) {
        this.speedUnits = speedUnits;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getVisibility() {
        return visibility;
    }

    public void setVisibility(float visibility) {
        this.visibility = visibility;
    }
}
