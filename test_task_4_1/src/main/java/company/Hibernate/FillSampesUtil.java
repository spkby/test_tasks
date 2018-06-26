package company.Hibernate;

import company.DAO.*;
import company.model.Account;
import company.model.Employee;
import company.model.Holiday;
import company.model.Salary;

import java.sql.Date;

public class FillSampesUtil {
    public static void main(String[] args) {
        try {
            RoleDAO roleDAO = new RoleDAO();
            DepartmentDAO departmentDAO = new DepartmentDAO();
            StatusDAO statusDAO = new StatusDAO();
            EmployeeDAO employeeDAO = new EmployeeDAO();
            AccountDAO accountDAO = new AccountDAO();
            SalaryDAO salaryDAO = new SalaryDAO();
            HolidayDAO holidayDAO = new HolidayDAO();

            Employee employee;
            Salary salary;
            Account account;
            Holiday holiday;

            // 1
            salary = new Salary();
            salary.setQuantity(3100);
            salaryDAO.add(salary);

            employee = new Employee();
            employee.setBirthday(Date.valueOf("1991-01-02"));
            employee.setName("Employee 1");
            employee.setRole(roleDAO.getById(1));
            employee.setDepartment(departmentDAO.getById(1));
            employee.setSalary(salary);
            employeeDAO.add(employee);

            account = new Account();
            account.setEmployee(employee);
            account.setLogin("employee1");
            account.setPass("123");
            accountDAO.add(account);

            holiday = new Holiday();
            holiday.setEmployee(employee);
            holiday.setDateFrom(Date.valueOf("2018-08-01"));
            holiday.setDateTo(Date.valueOf("2018-08-11"));
            holiday.setStatus(statusDAO.getById(1));
            holidayDAO.add(holiday);

            // 2
            salary = new Salary();
            salary.setQuantity(3100);
            salaryDAO.add(salary);

            employee = new Employee();
            employee.setBirthday(Date.valueOf("1991-01-02"));
            employee.setName("Employee 2");
            employee.setRole(roleDAO.getById(1));
            employee.setDepartment(departmentDAO.getById(1));
            employee.setSalary(salary);
            employeeDAO.add(employee);

            account = new Account();
            account.setEmployee(employee);
            account.setLogin("employee2");
            account.setPass("123");
            accountDAO.add(account);

            holiday = new Holiday();
            holiday.setEmployee(employee);
            holiday.setDateFrom(Date.valueOf("2018-08-02"));
            holiday.setDateTo(Date.valueOf("2018-08-12"));
            holiday.setStatus(statusDAO.getById(1));
            holidayDAO.add(holiday);

            // 3
            salary = new Salary();
            salary.setQuantity(2200);
            salaryDAO.add(salary);

            employee = new Employee();
            employee.setBirthday(Date.valueOf("1991-01-03"));
            employee.setName("Employee 3");
            employee.setRole(roleDAO.getById(2));
            employee.setDepartment(departmentDAO.getById(2));
            employee.setSalary(salary);
            employeeDAO.add(employee);

            account = new Account();
            account.setEmployee(employee);
            account.setLogin("employee3");
            account.setPass("123");
            accountDAO.add(account);

            holiday = new Holiday();
            holiday.setEmployee(employee);
            holiday.setDateFrom(Date.valueOf("2018-08-03"));
            holiday.setDateTo(Date.valueOf("2018-08-13"));
            holiday.setStatus(statusDAO.getById(1));
            holidayDAO.add(holiday);

            // 4
            salary = new Salary();
            salary.setQuantity(1100);
            salaryDAO.add(salary);

            employee = new Employee();
            employee.setBirthday(Date.valueOf("1991-01-04"));
            employee.setName("Employee 4");
            employee.setRole(roleDAO.getById(3));
            employee.setDepartment(departmentDAO.getById(2));
            employee.setSalary(salary);
            employeeDAO.add(employee);

            account = new Account();
            account.setEmployee(employee);
            account.setLogin("employee4");
            account.setPass("123");
            accountDAO.add(account);

            holiday = new Holiday();
            holiday.setEmployee(employee);
            holiday.setDateFrom(Date.valueOf("2018-08-04"));
            holiday.setDateTo(Date.valueOf("2018-08-14"));
            holiday.setStatus(statusDAO.getById(1));
            holidayDAO.add(holiday);

            // 5
            salary = new Salary();
            salary.setQuantity(1000);
            salaryDAO.add(salary);

            employee = new Employee();
            employee.setBirthday(Date.valueOf("1991-01-05"));
            employee.setName("Employee 5");
            employee.setRole(roleDAO.getById(3));
            employee.setDepartment(departmentDAO.getById(2));
            employee.setSalary(salary);
            employeeDAO.add(employee);

            account = new Account();
            account.setEmployee(employee);
            account.setLogin("employee5");
            account.setPass("123");
            accountDAO.add(account);

            holiday = new Holiday();
            holiday.setEmployee(employee);
            holiday.setDateFrom(Date.valueOf("2018-08-05"));
            holiday.setDateTo(Date.valueOf("2018-08-15"));
            holiday.setStatus(statusDAO.getById(1));
            holidayDAO.add(holiday);

            // 6
            salary = new Salary();
            salary.setQuantity(2100);
            salaryDAO.add(salary);

            employee = new Employee();
            employee.setBirthday(Date.valueOf("1991-01-06"));
            employee.setName("Employee 6");
            employee.setRole(roleDAO.getById(2));
            employee.setDepartment(departmentDAO.getById(3));
            employee.setSalary(salary);
            employeeDAO.add(employee);

            account = new Account();
            account.setEmployee(employee);
            account.setLogin("employee6");
            account.setPass("123");
            accountDAO.add(account);

            holiday = new Holiday();
            holiday.setEmployee(employee);
            holiday.setDateFrom(Date.valueOf("2018-08-06"));
            holiday.setDateTo(Date.valueOf("2018-08-16"));
            holiday.setStatus(statusDAO.getById(1));
            holidayDAO.add(holiday);

            // 7
            salary = new Salary();
            salary.setQuantity(1100);
            salaryDAO.add(salary);

            employee = new Employee();
            employee.setBirthday(Date.valueOf("1991-01-07"));
            employee.setName("Employee 7");
            employee.setRole(roleDAO.getById(3));
            employee.setDepartment(departmentDAO.getById(3));
            employee.setSalary(salary);
            employeeDAO.add(employee);

            account = new Account();
            account.setEmployee(employee);
            account.setLogin("employee7");
            account.setPass("123");
            accountDAO.add(account);

            holiday = new Holiday();
            holiday.setEmployee(employee);
            holiday.setDateFrom(Date.valueOf("2018-08-07"));
            holiday.setDateTo(Date.valueOf("2018-08-17"));
            holiday.setStatus(statusDAO.getById(1));
            holidayDAO.add(holiday);

            // 8
            salary = new Salary();
            salary.setQuantity(1000);
            salaryDAO.add(salary);

            employee = new Employee();
            employee.setBirthday(Date.valueOf("1991-01-08"));
            employee.setName("Employee 8");
            employee.setRole(roleDAO.getById(3));
            employee.setDepartment(departmentDAO.getById(3));
            employee.setSalary(salary);
            employeeDAO.add(employee);

            account = new Account();
            account.setEmployee(employee);
            account.setLogin("employee8");
            account.setPass("123");
            accountDAO.add(account);

            holiday = new Holiday();
            holiday.setEmployee(employee);
            holiday.setDateFrom(Date.valueOf("2018-08-08"));
            holiday.setDateTo(Date.valueOf("2018-08-18"));
            holiday.setStatus(statusDAO.getById(1));
            holidayDAO.add(holiday);
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
