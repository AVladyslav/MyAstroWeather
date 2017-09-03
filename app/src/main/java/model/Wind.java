package model;

import java.io.Serializable;

/**
 * Created by Szaman on 2017-06-21.
 */

public class Wind implements Serializable
{
    private String windForce = null;
    private String windDirection = null;
    private String humidity = null;
    private String visibility = null;

    public Wind(String humidity, String visibility, String windDirection, String windForce)
    {
        this.humidity = humidity;
        this.visibility = visibility;
        this.windDirection = windDirection;
        this.windForce = windForce;
    }

    public String getHumidity()
    {
        return humidity;
    }

    public void setHumidity(String humidity)
    {
        this.humidity = humidity;
    }

    public String getVisibility()
    {
        return visibility;
    }

    public void setVisibility(String visibility)
    {
        this.visibility = visibility;
    }

    public String getWindDirection()
    {
        return windDirection;
    }

    public void setWindDirection(String windDirection)
    {
        this.windDirection = windDirection;
    }

    public String getWindForce()
    {
        return windForce;
    }

    public void setWindForce(String windForce)
    {
        this.windForce = windForce;
    }
}