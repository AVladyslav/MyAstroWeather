package utility;

import com.astrocalculator.AstroDateTime;

/**
 * Created by Szaman on 2017-05-27.
 */
public class DateAndTimeFormat
{
    public static String getFormattedDateAndTime(AstroDateTime time) //Wymuszenie wyswietlenia daty jako: DD:MM:YYY, HH:MM:SS
    {
        StringBuilder formattedTime = new StringBuilder();
        formattedTime.append(String.format("%02d", time.getDay())).append(".");
        formattedTime.append(String.format("%02d", time.getMonth())).append(".");
        formattedTime.append(String.format("%04d", time.getYear())).append(" ");
        formattedTime.append(String.format("%02d", time.getHour())).append(":");
        formattedTime.append(String.format("%02d", time.getMinute())).append(":");
        formattedTime.append(String.format("%02d", time.getSecond()));
        return formattedTime.toString();
    }

}