package by.spk.beans;

import by.spk.Constants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Department {

    private int id;
    private String name;

    public Department(int id, String name) {
        setId(id);
        setName(name);
    }

    public Department() {
    }

    public Department(ResultSet rs) throws SQLException {
        this(rs.getInt(1), rs.getString(2));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + Constants.DELIMITER + name;
    }
}