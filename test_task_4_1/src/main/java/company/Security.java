package company;

import company.DAO.AccountDAO;
import company.DAO.EmployeeDAO;
import company.DAO.HolidayDAO;
import company.model.Employee;
import company.model.Holiday;

public class Security {

    public enum RoleId {
        MANAGER, LEAD, REGULAR
    }

    public static RoleId getRoleId(String login) {
        return RoleId.values()[new AccountDAO().getAccountByLogin(login).getEmployee().getRole().getId() - 1];
    }

    public static boolean checkLoginToHolidayId(String login, int id) {

        Employee currEmployee = new AccountDAO().getAccountByLogin(login).getEmployee();
        Holiday holiday = new HolidayDAO().getById(id);

        return currEmployee.getId() == holiday.getEmployee().getId();
    }

    public static boolean checkLoginToHolidayIdToView(String login, int id) {

        Employee currEmployee = new AccountDAO().getAccountByLogin(login).getEmployee();

        Holiday holiday = new HolidayDAO().getById(id);

        return currEmployee.getId() == holiday.getEmployee().getId() || getRoleId(login) == RoleId.MANAGER
                || (currEmployee.getDepartment().getId() == holiday.getEmployee().getDepartment().getId()
                && getRoleId(login) == RoleId.LEAD);
    }

    public static boolean checkLoginToEmployeeId(String login, int id) {

        Employee currEmployee = new AccountDAO().getAccountByLogin(login).getEmployee();
        Employee employee = new EmployeeDAO().getById(id);

        return currEmployee.getId() == employee.getId() || getRoleId(login) == RoleId.MANAGER
                || (currEmployee.getDepartment().getId() == employee.getDepartment().getId()
                && getRoleId(login) == RoleId.LEAD);
    }

    public static boolean checkLoginToDepartmentId(String login, int id) {

        return new AccountDAO().getAccountByLogin(login).getEmployee().getDepartment().getId() == id
                || getRoleId(login) == RoleId.MANAGER;
    }


}
