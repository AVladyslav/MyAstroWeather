package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Szaman on 2017-06-21.
 */

public class LocationData implements Serializable
{

    private String country = null;
    private String city = null;

    private String longitude = null;
    private String latitude = null;

    private Date lastUpdate = null;

    public LocationData(String country, String city, String longitude, String latitude, Date lastUpdate)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.lastUpdate = lastUpdate;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }
    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public Date getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }
}
