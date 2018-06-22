package by.spk.DAO;

import by.spk.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum ConnBuilder {

    INSTANCE;

    private final Connection cn;

    private final String CLASS_NAME = "org.postgresql.Driver";
    private final String DB_URL = Utils.getPropertiesValue("db_url");
    private final String DB_USER = Utils.getPropertiesValue("db_username");
    private final String DB_PASS = Utils.getPropertiesValue("db_password");

    ConnBuilder(){
        try {
            Class.forName(CLASS_NAME);
            cn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static ConnBuilder getInstance(){
        return INSTANCE;
    }

    public Connection getConnection(){
        return cn;
    }

}

