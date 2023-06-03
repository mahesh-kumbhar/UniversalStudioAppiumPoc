package utilitys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeUtility
{
    protected static String dateTime ;


    public static String getDateTime()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        dateTime= dateFormat.format(date);
        return dateTime;
    }
    public static String randomString()
    {

       String strRandom=getDateTime().replace("-","");
        return strRandom;
    }


    public static String getNextMonth() {
        // get current date
        LocalDate currentDate = LocalDate.now();

        // add two months
        LocalDate nextNextMonth = currentDate.plusMonths(2);

        // format as "MMM yyyy" and return
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return nextNextMonth.format(formatter);
    }

}
