package by.spk.beans;

import by.spk.Constants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Salary {

    private int id;
    private int amount;
    private int employeeId;

    public Salary(int id, int amount, int employeeId) {
        setId(id);
        setAmount(amount);
        setEmployeeId(employeeId);
    }

    public Salary() {
    }

    public Salary(ResultSet rs) throws SQLException {
        this(rs.getInt(1), rs.getInt(2), rs.getInt(3));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return id +
                Constants.DELIMITER + amount + Constants.DELIMITER + employeeId;
    }
}
