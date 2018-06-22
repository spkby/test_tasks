package by.spk.beans;

import by.spk.Constants;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Holiday {

    private int id;
    private java.sql.Date dateFrom;
    private java.sql.Date dateTo;
    private int employeeId;

    public Holiday() {
    }

    public Holiday(int id, Date dateFrom, Date dateTo, int employeeId) {
        setId(id);
        setDateFrom(dateFrom);
        setDateTo(dateTo);
        setEmployeeId(employeeId);
    }

    public Holiday(ResultSet rs) throws SQLException {
        this(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getInt(4));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return id + Constants.DELIMITER + dateFrom + Constants.DELIMITER + dateTo + Constants.DELIMITER + employeeId;
    }
}
