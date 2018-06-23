package by.spk.DAO;

import by.spk.beans.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Executes {

    private static final String SELECT_POSITIONS = "SELECT * from positions";
    private static final String SELECT_DEPARTMENTS = "SELECT * from departments";
    private static final String SELECT_ACCOUNTS = "SELECT * from accounts";
    private static final String SELECT_HOLIDAYS = "SELECT * from holidays";
    private static final String SELECT_EMPLOYEES = "SELECT * from employees";

    private static final String SELECT_ACCOUNT_WHERE_ID = "SELECT * FROM accounts WHERE id_account = ?";
    private static final String SELECT_DEPARTMENT_WHERE_ID = "SELECT * FROM departments WHERE id_department = ?";
    private static final String SELECT_EMPLOYEE_WHERE_ID = "SELECT * FROM employees WHERE id_employee = ?";
    private static final String SELECT_HOLIDAY_WHERE_ID = "SELECT * FROM holidays WHERE id_holiday = ?";
    private static final String SELECT_POSITION_WHERE_ID = "SELECT * FROM positions WHERE id_position = ?";

    private static final String SELECT_ACCOUNT_WHERE_LOGIN = "SELECT * FROM accounts WHERE login = ?";
    private static final String SELECT_EMPLOYEES_WHERE_DEPARTMENT_ID = "SELECT * FROM employees WHERE department_id = ?";
    private static final String SELECT_HOLIDAYS_WHERE_EMPLOYEE_ID = "SELECT * FROM holidays WHERE employee_id = ?";

    private static final String SELECT_DEPARTMENT_ID_WHERE_ID_EMPLOYEE = "SELECT department_id FROM employees WHERE id_employee = ?";

    private static final String INSERT_HOLIDAY = "INSERT INTO holidays (date_from, date_to, employee_id, status_id) values (?,?,?,1)";
    private static final String UPDATE_HOLIDAY = "UPDATE holidays SET status_id = ? WHERE id_holiday = ?";
    private static final String DELETE_HOLIDAY = "DELETE FROM holidays WHERE id_holiday = ?";

    private static final String INSERT_EMPLOYEE = "INSERT INTO employees (name, birthday, position_id, department_id, salary) values (?,?,?,?,?)";
    private static final String UPDATE_EMPLOYEE = "UPDATE employees SET name = ?, birthday = ?, position_id = ?, department_id =?, salary = ? WHERE id_employee = ?";
    private static final String DELETE_EMPLOYEE = "DELETE FROM employees WHERE id_employee = ?";

    private static final String INSERT_ACCOUNT = "INSERT INTO accounts (login, pass, employee_id) values (?,?,?)";
    private static final String UPDATE_ACCOUNT = "UPDATE employees SET login = ?, pass = ?, employee_id = ? WHERE id_account = ?";
    private static final String DELETE_ACCOUNT = "DELETE FROM accounts WHERE id_account = ?";

    private static Connection cn;
    private static PreparedStatement st;
    private static ResultSet rs;

    static {
        cn = ConnBuilder.getInstance().getConnection();
    }

    static List selectFrom(Tables table) throws SQLException {

        String sql = null;
        List list = null;

        switch (table) {
            case ACCOUNT:
                list = new ArrayList<Account>();
                sql = SELECT_ACCOUNTS;
                break;
            case DEPARTMENT:
                list = new ArrayList<Department>();
                sql = SELECT_DEPARTMENTS;
                break;
            case EMPLOYEE:
                list = new ArrayList<Employee>();
                sql = SELECT_EMPLOYEES;
                break;
            case HOLIDAY:
                list = new ArrayList<Holiday>();
                sql = SELECT_HOLIDAYS;
                break;
            case POSITION:
                list = new ArrayList<Position>();
                sql = SELECT_POSITIONS;
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
            }
        }
        return list;
    }

    static <T> T selectWhereId(Tables table, int id) throws SQLException {

        String sql = null;
        T t = null;

        switch (table) {
            case ACCOUNT:
                sql = SELECT_ACCOUNT_WHERE_ID;
                break;
            case DEPARTMENT:
                sql = SELECT_DEPARTMENT_WHERE_ID;
                break;
            case EMPLOYEE:
                sql = SELECT_EMPLOYEE_WHERE_ID;
                break;
            case HOLIDAY:
                sql = SELECT_HOLIDAY_WHERE_ID;
                break;
            case POSITION:
                sql = SELECT_POSITION_WHERE_ID;
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
            }
        }

        return t;
    }

    static Account selectAccountWhereLogin(String login) throws SQLException {

        Account account = null;

        st = cn.prepareStatement(SELECT_ACCOUNT_WHERE_LOGIN);
        st.setString(1, login);
        rs = st.executeQuery();

        if (rs.next()) {
            account = new Account(rs);
        }

        return account;
    }

    static List<Employee> selectEmployeesWhereDepartmentId(int departmentId) throws SQLException {

        List<Employee> list = null;

        st = cn.prepareStatement(SELECT_EMPLOYEES_WHERE_DEPARTMENT_ID);
        st.setInt(1, departmentId);
        rs = st.executeQuery();

        while (rs.next()) {
            list.add(new Employee(rs));
        }
        return list;
    }


    static List<Holiday> selectHolidaysWhereEmployeeId(int employeeId) throws SQLException {
        List<Holiday> list = null;

        st = cn.prepareStatement(SELECT_HOLIDAYS_WHERE_EMPLOYEE_ID);
        st.setInt(1, employeeId);
        rs = st.executeQuery();

        while (rs.next()) {
            list.add(new Holiday(rs));
        }
        return list;
    }

    static int selectDepartmentIdWhereEmployeeId(int employeeId) throws SQLException {
        int department = -1;
        st = cn.prepareStatement(SELECT_DEPARTMENT_ID_WHERE_ID_EMPLOYEE);
        st.setInt(1, employeeId);
        rs = st.executeQuery();

        if (rs.next()) {
            department = rs.getInt(1);
        }
        return department;
    }

    static void deleteById(Tables table, int id) throws SQLException {
        String sql = null;

        switch (table) {
            case ACCOUNT:
                sql = DELETE_ACCOUNT;
                break;
            case EMPLOYEE:
                sql = DELETE_EMPLOYEE;
                break;
            case HOLIDAY:
                sql = DELETE_HOLIDAY;
                break;
        }

        st = cn.prepareStatement(sql);
        st.setInt(1, id);
        st.execute();
    }

    static void insertHoliday(Date dateFrom, Date dateTo, int employeeId) throws SQLException {
        st = cn.prepareStatement(INSERT_HOLIDAY);
        st.setDate(1, dateFrom);
        st.setDate(2, dateTo);
        st.setInt(3, employeeId);
        st.execute();
    }

    static void updateHolidayStatus(int holidayId, boolean status) throws SQLException {
        st = cn.prepareStatement(UPDATE_HOLIDAY);
        st.setBoolean(1, status);
        st.setInt(2, holidayId);
        st.execute();
    }

    static void insertEmployee(Employee employee) throws SQLException {
        st = cn.prepareStatement(INSERT_EMPLOYEE);
        st.setString(1, employee.getName());
        st.setDate(2, employee.getBirthday());
        st.setInt(3, employee.getPositionId());
        st.setInt(4, employee.getDepartmentId());
        st.setInt(5, employee.getSalary());
        st.execute();
    }

    static void updateEmployee(Employee employee) throws SQLException {
        st = cn.prepareStatement(UPDATE_EMPLOYEE);
        st.setString(1, employee.getName());
        st.setDate(2, employee.getBirthday());
        st.setInt(3, employee.getPositionId());
        st.setInt(4, employee.getDepartmentId());
        st.setInt(5, employee.getSalary());
        st.setInt(6, employee.getId());
        st.execute();
    }

    static void insertAccount(Account account) throws SQLException {
        st = cn.prepareStatement(INSERT_ACCOUNT);
        st.setString(1, account.getLogin());
        st.setString(2, account.getPass());
        st.setInt(3, account.getEmployeeId());
        st.execute();
    }

    static void updateAccount(Account account) throws SQLException {
        st = cn.prepareStatement(UPDATE_ACCOUNT);
        st.setString(1, account.getLogin());
        st.setString(2, account.getPass());
        st.setInt(3, account.getEmployeeId());
        st.setInt(4, account.getId());
        st.execute();
    }

}