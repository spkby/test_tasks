package by.spk.DAO;

import by.spk.beans.Account;
import by.spk.beans.Department;
import by.spk.beans.Employee;
import by.spk.beans.Holiday;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logic {

    public static boolean checkLoginAndPass(String login, String pass) throws SQLException {
        Account account = Executes.selectAccountWhereLogin(login);
        return pass.equals(account.getPass());
    }

    public static Employee getEmployeeByLogin(String login) throws SQLException {
        return Executes.selectWhereId(Tables.EMPLOYEE, Executes.selectAccountWhereLogin(login).getEmployeeId());
    }

    public static int getPositionByLogin(String login) throws SQLException {
        return getEmployeeByLogin(login).getPositionId();
    }

    public static Map<Employee, List<Holiday>> getHolidayByDepartment(Department department) throws SQLException {
        Map<Employee, List<Holiday>> map = new HashMap<>();

        for (Employee e : Executes.selectEmployeesWhereDepartmentId(department.getId())) {
            map.put(e, getHolidaysByEmployee(e));
        }
        return map;
    }

    public static Department getDepartmentByEmployee(Employee employee) throws SQLException {
        return Executes.selectWhereId(Tables.DEPARTMENT, Executes.selectDepartmentIdWhereEmployeeId(employee.getId()));
    }

    public static List<Holiday> getHolidaysByEmployee(Employee employee) throws SQLException {
        return Executes.selectHolidaysWhereEmployeeId(employee.getId());
    }

    public static List<Holiday> getHolidaysByLogin(String login) throws SQLException {
        return getHolidaysByEmployee(getEmployeeByLogin(login));
    }

    public static void addHoliday(Date dateFrom, Date dateTo, String login) throws SQLException {
        Executes.insertHoliday(dateFrom, dateTo, getEmployeeByLogin(login).getId());
    }

    public static void changeHolidayStatus(Holiday holiday, Holiday.Status status) {

    }


}
