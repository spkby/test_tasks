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
    private Status status;

    public enum Status {
        PENDING(1),
        ACCEPTED(2),
        DENIED(3);

        private final int value;

        Status(int value) {
            this.value = value;
        }

        public int get() {
            return value;
        }
    }

    public Holiday() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = Status.values()[status];
    }

    public Holiday(int id, Date dateFrom, Date dateTo, int employeeId, int status) {
        setId(id);
        setDateFrom(dateFrom);
        setDateTo(dateTo);
        setEmployeeId(employeeId);
        setStatus(status);
    }

    public Holiday(ResultSet rs) throws SQLException {
        this(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getInt(4), rs.getInt(5));
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
