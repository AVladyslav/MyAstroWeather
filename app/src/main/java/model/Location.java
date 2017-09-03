package model;

/**
 * Created by Szaman on 2017-06-21.
 */

import java.io.Serializable;

public class Location implements Serializable{

    private String country = null;
    private String city = null;

    public Location(String city, String country)
    {
        this.city = city;
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

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    @Override
    public String toString()
    {
        return city + ", " + country;
    }
}
