package model;

import java.io.Serializable;

/**
 * Created by Szaman on 2017-06-21.
 */

public class ActualWeather implements Serializable
{

    private String temperature = null;
    private String pressure = null;
    private String description = null;
    private String imageCode = null;

    public ActualWeather(String temperature, String pressure, String description, String imageCode)
    {
        this.temperature = temperature;
        this.pressure = pressure;
        this.description = description;
        this.imageCode = imageCode;
    }

    public String getTemperature()
    {
        return temperature;
    }

    public void setTemperature(String temperature)
    {
        this.temperature = temperature;
    }

    public String getPressure()
    {
        return pressure;
    }

    public void setPressure(String pressure)
    {
        this.pressure = pressure;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getImageCode()
    {
        return imageCode;
    }

    public void setImageCode(String imageCode)
    {
        this.imageCode = imageCode;
    }
}
