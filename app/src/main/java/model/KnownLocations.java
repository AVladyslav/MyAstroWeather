package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Szaman on 2017-06-21.
 */

public class KnownLocations implements Serializable
{

    private List<Location> locationList = null;

    public KnownLocations()
    {
        this.locationList = new ArrayList<>();
    }

    public KnownLocations(List<Location> localizations)
    {
        this.locationList = localizations;
    }

    public void addLocation(Location location)
    {
        if (locationList == null)
        {
            locationList = new ArrayList<>();
        }
        locationList.add(location);
    }

    public List<Location> getLocationList()
    {
        return locationList;
    }

    public void setLocationList(List<Location> locationList)
    {
        this.locationList = locationList;
    }

    public String[] getArray()
    {
        String[] locationsArray = new String[locationList.size()];
        for (int i = 0; i < locationList.size(); i++)
        {
            locationsArray[i] = locationList.get(i).toString();
        }
        return locationsArray;
    }

    public boolean isLocationExists(String location)
    {
        if (location == null || locationList.size() == 0)
        {
            return false;
        }
        for (String locationFromArray : getArray())
        {
            if (locationFromArray.equals(location))
            {
                return true;
            }
        }
        return false;
    }
}