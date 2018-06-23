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
    private int salary;

    public Employee(int id, String name, Date birthday, int positionId, int departmentId, int salary) {
        setId(id);
        setName(name);
        setBirthday(birthday);
        setPositionId(positionId);
        setDepartmentId(departmentId);
        setSalary(salary);
    }

    public Employee(ResultSet rs) throws SQLException {
        this(rs.getInt(1), rs.getString(2), rs.getDate(3),
                rs.getInt(4), rs.getInt(5), rs.getInt(6));
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return id + Constants.DELIMITER + name + Constants.DELIMITER + birthday
                + Constants.DELIMITER + positionId + Constants.DELIMITER + departmentId;
    }
}
