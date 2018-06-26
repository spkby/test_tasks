package company;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {

    public static java.sql.Date getDate(String strDate) {
        try {
            return java.sql.Date.valueOf(strDate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("invalid date format");
        }
    }

    public static boolean date2IsMoreThanDate1(String date1, String date2) {
        try {
            return getDate(date1).compareTo(getDate(date2)) >= 0;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("invalid date format");
        }
    }

    public static boolean dateIsMoreThanToday(String date) {
        try {
            return date2IsMoreThanDate1(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()), date);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("invalid date format");
        }
    }
}
