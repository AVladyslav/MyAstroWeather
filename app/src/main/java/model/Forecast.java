package model;

import java.io.Serializable;

/**
 * Created by Szaman on 2017-06-21.
 */

public class Forecast implements Serializable
{
    private String minimalTemperature = null;
    private String maximalTemperature = null;

    private String day = null;
    private String imageCode = null;

    public Forecast(String minTemperature, String maxTemperature, String day, String imageCode) {
        this.minimalTemperature = minTemperature;
        this.maximalTemperature = maxTemperature;
        this.day = day;
        this.imageCode = imageCode;
    }

    public String getMinimalTemperature()
    {
        return minimalTemperature;
    }

    public void setMinimalTemperature(String minimalTemperature)
    {
        this.minimalTemperature = minimalTemperature;
    }

    public String getMaximalTemperature()
    {
        return maximalTemperature;
    }

    public void setMaximalTemperature(String maximalTemperature)
    {
        this.maximalTemperature = maximalTemperature;
    }

    public String getDay()
    {
        return day;
    }

    public void setDay(String day)
    {
        this.day = day;
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