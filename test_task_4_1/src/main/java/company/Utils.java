package company;

public class Utils {

    public static java.sql.Date getDate(String strDate) {
        try {
            return java.sql.Date.valueOf(strDate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("invalid date format");
        }

    }

}
