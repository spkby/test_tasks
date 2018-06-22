package by.spk.beans;

import by.spk.Constants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {

    private int id;
    private String login;
    private String pass;
    private int employeeId;

    public Account() {
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Account(ResultSet rs) throws SQLException {
        this(rs.getInt(1), rs.getString(2),
                rs.getString(3), rs.getInt(4));
    }

    public Account(int id, String login, String pass, int employeeId) {
        setId(id);
        setLogin(login);
        setPass(pass);
        setEmployeeId(employeeId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getEmployeeId() {
        return employeeId;
    }


    @Override
    public String toString() {
        return id + Constants.DELIMITER + login + Constants.DELIMITER + pass + Constants.DELIMITER + employeeId;
    }
}
