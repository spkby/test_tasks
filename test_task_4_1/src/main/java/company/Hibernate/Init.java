package company.Hibernate;

import company.DAO.*;
import company.Utils;
import company.entity.*;

public class Init {

    public static void main(String[] args) {

        // fillConstantData();

        // fillData();

        DepartmentDAO departmentDAO = new DepartmentDAO();
        departmentDAO.getList().stream().forEach(System.out::println);
        System.out.println();

        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.getListByDepartment(departmentDAO.getById(3)).stream()
                .forEach(System.out::println);
        System.out.println();

        HolidayDAO holidayDAO = new HolidayDAO();
        holidayDAO.getListByEmployee(employeeDAO.getById(2)).stream().forEach(System.out::println);
        System.out.println();


        HibernateUtil.shutdown();

    }

    private static void fillConstantData() {

        IDAO dao;

        dao = new StatusDAO();
        Status status = new Status();
        status.setName("pending");
        new StatusDAO().add(status);

        status = new Status();
        status.setName("accepted");
        new StatusDAO().add(status);

        status = new Status();
        status.setName("denied");
        new StatusDAO().add(status);

        dao = new RoleDAO();
        Role role = new Role();
        role.setName("manager");
        dao.add(role);

        role = new Role();
        role.setName("lead");
        dao.add(role);

        role = new Role();
        role.setName("regular");
        dao.add(role);

        dao = new DepartmentDAO();
        Department department = new Department();
        department.setName("Management");
        dao.add(department);

        department = new Department();
        department.setName("Finance");
        dao.add(department);

        department = new Department();
        department.setName("IT");
        dao.add(department);
    }

    private static void fillData() {

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
        salary.setQuantity(3300);
        salaryDAO.add(salary);

        employee = new Employee();
        employee.setBirthday(Utils.toDate("01-01-1991"));
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
        holiday.setDateFrom(Utils.toDate("01-08-2018"));
        holiday.setDateTo(Utils.toDate("11-08-2018"));
        holiday.setStatus(statusDAO.getById(1));
        holidayDAO.add(holiday);

        // 2
        salary = new Salary();
        salary.setQuantity(3100);
        salaryDAO.add(salary);

        employee = new Employee();
        employee.setBirthday(Utils.toDate("02-01-1991"));
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
        holiday.setDateFrom(Utils.toDate("02-08-2018"));
        holiday.setDateTo(Utils.toDate("12-08-2018"));
        holiday.setStatus(statusDAO.getById(1));
        holidayDAO.add(holiday);

        // 3
        salary = new Salary();
        salary.setQuantity(2200);
        salaryDAO.add(salary);

        employee = new Employee();
        employee.setBirthday(Utils.toDate("03-01-1991"));
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
        holiday.setDateFrom(Utils.toDate("03-08-2018"));
        holiday.setDateTo(Utils.toDate("13-08-2018"));
        holiday.setStatus(statusDAO.getById(1));
        holidayDAO.add(holiday);

        // 4
        salary = new Salary();
        salary.setQuantity(1100);
        salaryDAO.add(salary);

        employee = new Employee();
        employee.setBirthday(Utils.toDate("04-01-1991"));
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
        holiday.setDateFrom(Utils.toDate("04-08-2018"));
        holiday.setDateTo(Utils.toDate("14-08-2018"));
        holiday.setStatus(statusDAO.getById(1));
        holidayDAO.add(holiday);

        // 5
        salary = new Salary();
        salary.setQuantity(1000);
        salaryDAO.add(salary);

        employee = new Employee();
        employee.setBirthday(Utils.toDate("05-01-1991"));
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
        holiday.setDateFrom(Utils.toDate("05-08-2018"));
        holiday.setDateTo(Utils.toDate("15-08-2018"));
        holiday.setStatus(statusDAO.getById(1));
        holidayDAO.add(holiday);

        // 6
        salary = new Salary();
        salary.setQuantity(2100);
        salaryDAO.add(salary);

        employee = new Employee();
        employee.setBirthday(Utils.toDate("06-01-1991"));
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
        holiday.setDateFrom(Utils.toDate("06-08-2018"));
        holiday.setDateTo(Utils.toDate("16-08-2018"));
        holiday.setStatus(statusDAO.getById(1));
        holidayDAO.add(holiday);

        // 7
        salary = new Salary();
        salary.setQuantity(1100);
        salaryDAO.add(salary);

        employee = new Employee();
        employee.setBirthday(Utils.toDate("07-01-1991"));
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
        holiday.setDateFrom(Utils.toDate("07-08-2018"));
        holiday.setDateTo(Utils.toDate("17-08-2018"));
        holiday.setStatus(statusDAO.getById(1));
        holidayDAO.add(holiday);

        // 8
        salary = new Salary();
        salary.setQuantity(1000);
        salaryDAO.add(salary);

        employee = new Employee();
        employee.setBirthday(Utils.toDate("08-01-1991"));
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
        holiday.setDateFrom(Utils.toDate("08-08-2018"));
        holiday.setDateTo(Utils.toDate("18-08-2018"));
        holiday.setStatus(statusDAO.getById(1));
        holidayDAO.add(holiday);
    }
}