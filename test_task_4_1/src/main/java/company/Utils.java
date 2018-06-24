package company;

import java.io.IOException;

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

}
