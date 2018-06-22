package by.spk;

import by.spk.DAO.Executes;
import by.spk.DAO.Tables;
import by.spk.beans.Account;
import by.spk.beans.Department;
import by.spk.beans.Employee;
import by.spk.beans.Holiday;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logic {

    public static boolean checkLoginAndPass(String login, String pass) throws SQLException {
        Account account = Executes.getAccountByLogin(login);
        return pass.equals(account.getPass());
    }

    public static Employee getEmployeeByLogin(String login) throws SQLException {
        return Executes.getById(Tables.EMPLOYEE, Executes.getAccountByLogin(login).getEmployeeId());
    }

    public static int getPositionByLogin(String login) throws SQLException {
        return getEmployeeByLogin(login).getPositionId();
    }

    public static Map<Employee, List<Holiday>> getHolidayByDepartment(Department department) throws SQLException {
        Map<Employee, List<Holiday>> map = new HashMap<>();

        for (Employee e : Executes.getEmployeesByDepartmentId(department.getId())) {
            map.put(e, getHolidaysByEmployee(e));
        }
        return map;
    }

    public static Department getDepartmentByEmployee(Employee employee) throws SQLException {
        return Executes.getById(Tables.DEPARTMENT, Executes.getDepartmentIdByEmployeeId(employee.getId()));
    }

    public static List<Holiday> getHolidaysByEmployee(Employee employee) throws SQLException {
        return Executes.getHolidaysByEmployeeId(employee.getId());
    }

}
