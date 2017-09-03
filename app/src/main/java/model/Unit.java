package model;

import java.io.Serializable;

/**
 * Created by Szaman on 2017-06-21.
 */

public class Unit implements Serializable
{
    private String pressure = null;
    private String windSpeed = null;
    private String temperature = null;
    private String visibility = null;

    public Unit(String pressure, String windSpeed, String temperature, String visibility)
    {
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.temperature = temperature;
        this.visibility = visibility;
    }

    public String getPressure()
    {
        return pressure;
    }

    public void setPressure(String pressure)
    {
        this.pressure = pressure;
    }

    public String getWindSpeed()
    {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed)
    {
        this.windSpeed = windSpeed;
    }

    public String getTemperature()
    {
        return temperature;
    }

    public void setTemperature(String temperature)
    {
        this.temperature = temperature;
    }

    public String getVisibility()
    {
        return visibility;
    }

    public void setVisibility(String visibility)
    {
        this.visibility = visibility;
    }
}
