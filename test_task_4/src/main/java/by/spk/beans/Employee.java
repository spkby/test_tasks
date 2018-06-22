package by.spk.beans;

import by.spk.Constants;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee {

    private int id;
    private String name;
    private java.sql.Date birthday;
    private int positionId;
    private int departmentId;

    public Employee(int id, String name, Date birthday, int positionId, int departmentId) {
        setId(id);
        setName(name);
        setBirthday(birthday);
        setPositionId(positionId);
        setDepartmentId(departmentId);
    }

    public Employee(ResultSet rs) throws SQLException {
        this(rs.getInt(1), rs.getString(2),
                rs.getDate(3), rs.getInt(4), rs.getInt(5));
    }

    public Employee() {
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return id + Constants.DELIMITER + name + Constants.DELIMITER + birthday
                + Constants.DELIMITER + positionId + Constants.DELIMITER + departmentId;
    }
}
