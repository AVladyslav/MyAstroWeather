package utility;

import model.KnownLocations;
import model.Location;
import model.Weather;

/**
 * Created by Ana Maria Paula on 2017-06-21.
 */
public class DefaultValue
{
    public static int defaultRefreshRatio = 10;
    public static double defaultLongitude = 19.456;                         // easter egg ;d
    public static double defaultLatitude = 51.747;                          //
    public static Location defaultLocation = new Location("lodz", "pl");    //

    public static Location selectedLocation = new Location("lodz", "pl");
    public static char system = 'c';

    public static int defaultDataRefreshRatio = 10; //minuty

///!!!!!!!!!!!!!!!!!!!!!!///
    public static final String[] WEATHER_URL =
        {
            "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20u%3D%22",
            "%22%20and%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22",
            "%2C%20",
            "%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys"
        };

    public static Weather weather = null;
    public static KnownLocations locations = new KnownLocations();


}