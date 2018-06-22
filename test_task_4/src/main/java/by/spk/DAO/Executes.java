package by.spk.DAO;

import by.spk.beans.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Executes {

    private static Connection cn;
    private static PreparedStatement st;
    private static ResultSet rs;

    static {
        cn = ConnBuilder.getInstance().getConnection();
    }

    public static List getList(Tables table) throws SQLException {

        String sql = null;
        List list = null;

        switch (table) {
            case ACCOUNT:
                list = new ArrayList<Account>();
                sql = SQLQueries.SELECT_ACCOUNTS;
                break;
            case DEPARTMENT:
                list = new ArrayList<Department>();
                sql = SQLQueries.SELECT_DEPARTMENTS;
                break;
            case EMPLOYEE:
                list = new ArrayList<Employee>();
                sql = SQLQueries.SELECT_EMPLOYEES;
                break;
            case HOLIDAY:
                list = new ArrayList<Holiday>();
                sql = SQLQueries.SELECT_HOLIDAYS;
                break;
            case POSITION:
                list = new ArrayList<Position>();
                sql = SQLQueries.SELECT_POSITIONS;
                break;
            case SALARY:
                list = new ArrayList<Salary>();
                sql = SQLQueries.SELECT_SALARIES;
                break;
        }

        st = cn.prepareStatement(sql);
        rs = st.executeQuery();

        while (rs.next()) {
            switch (table) {
                case ACCOUNT:
                    list.add(new Account(rs));
                    break;
                case DEPARTMENT:
                    list.add(new Department(rs));
                    break;
                case EMPLOYEE:
                    list.add(new Employee(rs));
                    break;
                case HOLIDAY:
                    list.add(new Holiday(rs));
                    break;
                case POSITION:
                    list.add(new Position(rs));
                    break;
                case SALARY:
                    list.add(new Salary(rs));
                    break;
            }
        }
        return list;
    }

    public static <T> T getById(Tables table, int id) throws SQLException {

        String sql = null;
        T t = null;

        switch (table) {
            case ACCOUNT:
                sql = SQLQueries.SELECT_ACCOUNT_WHERE_ID;
                break;
            case DEPARTMENT:
                sql = SQLQueries.SELECT_DEPARTMENT_WHERE_ID;
                break;
            case EMPLOYEE:
                sql = SQLQueries.SELECT_EMPLOYEE_WHERE_ID;
                break;
            case HOLIDAY:
                sql = SQLQueries.SELECT_HOLIDAY_WHERE_ID;
                break;
            case POSITION:
                sql = SQLQueries.SELECT_POSITION_WHERE_ID;
                break;
            case SALARY:
                sql = SQLQueries.SELECT_SALARY_WHERE_ID;
                break;
        }

        st = cn.prepareStatement(sql);
        st.setInt(1, id);
        rs = st.executeQuery();

        if (rs.next()) {
            switch (table) {
                case ACCOUNT:
                    t = (T) new Account(rs);
                    break;
                case DEPARTMENT:
                    t = (T) new Department(rs);
                    break;
                case EMPLOYEE:
                    t = (T) new Employee(rs);
                    break;
                case HOLIDAY:
                    t = (T) new Holiday(rs);
                    break;
                case POSITION:
                    t = (T) new Position(rs);
                    break;
                case SALARY:
                    t = (T) new Salary(rs);
                    break;
            }
        }

        return t;
    }

    public static Account getAccountByLogin(String login) throws SQLException {

        Account account = null;

        st = cn.prepareStatement(SQLQueries.SELECT_ACCOUNT_WHERE_LOGIN);
        st.setString(1, login);
        rs = st.executeQuery();

        if (rs.next()) {
            account = new Account(rs);
        }

        return account;
    }

    public static List<Employee> getEmployeesByDepartmentId(int departmentId) throws SQLException {

        List<Employee> list = null;

        st = cn.prepareStatement(SQLQueries.SELECT_EMPLOYEE_WHERE_DEPARTMENT_ID);
        st.setInt(1, departmentId);
        rs = st.executeQuery();

        while (rs.next()) {
            list.add(new Employee(rs));
        }
        return list;
    }


    public static List<Holiday> getHolidaysByEmployeeId(int employeeId) throws SQLException {
        List<Holiday> list = null;

        st = cn.prepareStatement(SQLQueries.SELECT_HOLIDAYS_WHERE_EMPLOYEE_ID);
        st.setInt(1, employeeId);
        rs = st.executeQuery();

        while (rs.next()) {
            list.add(new Holiday(rs));
        }
        return list;
    }

    public static int getDepartmentIdByEmployeeId(int employeeId) throws SQLException {
        int department = -1;

        st = cn.prepareStatement(SQLQueries.SELECT_DEPARTMENT_ID_WHERE_ID_EMPLOYEE);
        st.setInt(1, employeeId);
        rs = st.executeQuery();

        if (rs.next()) {
            department = rs.getInt(1);
        }

        return department;

    }

}