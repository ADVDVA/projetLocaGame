package adrien.faouzi.utility;

import adrien.faouzi.managedBeans.I18nBean;
import adrien.faouzi.managedBeans.StoreBean;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Logger;

public class UtilityProcessing {
    private UtilityProcessing(){}

    /**
     * function to convert a localdatetime to a string.
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String localdatetimeInPattern (LocalDateTime localDateTime, String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    /**
     * function to convert a localdatetime to a string format FR.
     * @param localDateTime
     * @return
     */
    public static String localdatetimeInPattern (LocalDateTime localDateTime){
        return UtilityProcessing.localdatetimeInPattern(localDateTime, "dd/MM/yyyy");
    }

    /**
     * function to convert a localdatetime to a date.
     * @param localDateTime
     * @return
     */
    public static Date castLocalDateTimeToDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }


    /**
     * function to debug a string in console.
     * @param message
     * @return
     */
    public static void debug (String message){
        Logger log = Logger.getAnonymousLogger();
        log.info("debug ----------------> "+message);
    }



    public static String floatToStrTwoDigit(float number){
        String outStr = String.valueOf(Math.floor(number*100));
        return outStr.substring(0, outStr.length()-4)+"."+outStr.substring(outStr.length()-4, outStr.length()-2);
    }
}
