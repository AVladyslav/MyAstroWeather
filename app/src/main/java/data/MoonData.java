package data;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.Calendar;

import utility.DefaultValue;

/**
 * Created by Ana Maria Paula on 2017-06-27.
 */
public class MoonData
{

    private AstroCalculator.MoonInfo moonInformation;
    private AstroCalculator astroCalculator;

    public MoonData()
    {
        AstroDateTime astroDateTime = new AstroDateTime();
        Calendar calendar = Calendar.getInstance();

        astroDateTime.setSecond(calendar.get(Calendar.SECOND));         //sekundy
        astroDateTime.setMinute(calendar.get(Calendar.MINUTE));         //minuty
        astroDateTime.setHour(calendar.get(Calendar.HOUR_OF_DAY));      //godziny
        astroDateTime.setDay(calendar.get(Calendar.DAY_OF_MONTH));      //dni
        astroDateTime.setMonth(calendar.get(Calendar.MONTH) + 1);       //miesiace
        astroDateTime.setYear(calendar.get(Calendar.YEAR));             //lata

        astroDateTime.setTimezoneOffset((calendar.get(Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET)) / (3600 * 1000));  //strefa czasowa

        AstroCalculator.Location location = new AstroCalculator.Location(DefaultValue.defaultLatitude, DefaultValue.defaultLongitude);    //lokacja na podstawie dlugosci i szerokosci geograficznej

        astroCalculator = new AstroCalculator(astroDateTime, location);

        astroCalculator.setDateTime(astroDateTime);
        astroCalculator.setLocation(location);

        moonInformation = astroCalculator.getMoonInfo();
    }

    public double getAge()
    {
        return moonInformation.getAge();
    }

    public double getIllumination()
    {
        return moonInformation.getIllumination();
    }

    public AstroDateTime getMoonrise()
    {
        return moonInformation.getMoonrise();
    }

    public AstroDateTime getMoonset()
    {
        return moonInformation.getMoonset();
    }

    public AstroDateTime getNextFullMoon()
    {
        return moonInformation.getNextFullMoon();
    }

    public AstroDateTime getNextNewMoon()
    {
        return moonInformation.getNextNewMoon();
    }

}
