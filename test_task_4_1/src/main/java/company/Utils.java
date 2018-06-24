package company;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {

    private static final String PROPERTIES_FILE_PATH = "company.properties";

    public static String getPropertiesValue(String key) {
        java.util.Properties properties = new java.util.Properties();
        String value = null;
        try {
            properties.load(Utils.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_PATH));
            value = properties.getProperty(key);
        } catch (IOException e) {
            System.err.println(e);
        }
        return value;
    }

    public static Date toDate(String str){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            System.err.println(e);        }
        return new java.sql.Date(date.getTime());
    }

}
