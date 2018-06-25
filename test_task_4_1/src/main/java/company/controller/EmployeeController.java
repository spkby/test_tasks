package company.controller;

import company.DAO.*;
import company.Security;
import company.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
public class EmployeeController extends AbstractController {

    private AccountDAO accountDAO = new AccountDAO();
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private RoleDAO roleDAO = new RoleDAO();
    private SalaryDAO salaryDAO = new SalaryDAO();

    @GetMapping("/employee/view")
    public String getEmployeeView(@CookieValue(value = "login", defaultValue = "") String login, Model model) {

        model = accountForJSP(login, model);

        if (login.isEmpty()) {
            return "redirect:/login";
        }

        if (Security.getRoleId(login) == Security.RoleId.MANAGER) {
            model.addAttribute("employees", employeeDAO.getList());
            return "employee/list";
        } else if (Security.getRoleId(login) == Security.RoleId.LEAD) {
            return "redirect:/department/view/";
        } else {
            return getEmployeeMeView(login, model);
        }
    }

    @GetMapping("/employee/view/")
    public String getEmployeeMeView(@CookieValue(value = "login", defaultValue = "") String login, Model model) {
        model = accountForJSP(login, model);

        if (login.isEmpty()) {
            return "redirect:/login";
        }
        int id = accountDAO.getAccountByLogin(login).getEmployee().getId();
        return getEmployeeByIdView(login, id, model);
    }

    @GetMapping("/employee/view/{id}")
    public String getEmployeeByIdView(@CookieValue(value = "login", defaultValue = "") String login,
                                      @PathVariable(value = "id") int id, Model model) {
        model = accountForJSP(login, model);


        if (login.isEmpty() || !Security.checkLoginToEmployeeId(login, id)) {
            return "redirect:/login";
        }

        Employee employee = employeeDAO.getById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("loginEmployee", accountDAO.getAccountByEmployee(employee).getLogin());
        model.addAttribute("currLogin", login);
        return "employee/view";
    }

    @GetMapping("/employee/add")
    public String getEmployeeAdd(@CookieValue(value = "login", defaultValue = "") String login, Model model) {

        model = accountForJSP(login, model);

        if (login.isEmpty() || (Security.getRoleId(login) != Security.RoleId.MANAGER)) {
            return "redirect:/login";
        }

        model.addAttribute("departments", departmentDAO.getList());
        model.addAttribute("roles", roleDAO.getList());

        return "/employee/add";
    }

    @PostMapping("/employee/add")
    public String addEmployee(@CookieValue(value = "login", defaultValue = "") String login,
                              @RequestParam(value = "name") String employeeName,
                              @RequestParam(value = "birthday") String employeeBirthday,
                              @RequestParam(value = "department") String employeeDepartment,
                              @RequestParam(value = "role") String employeeRole,
                              @RequestParam(value = "login") String employeeLogin,
                              @RequestParam(value = "pass") String employeePass,
                              @RequestParam(value = "salary") String employeeSalary) {

        if (login.isEmpty() || !(Security.getRoleId(login) == Security.RoleId.MANAGER)) {
            return "redirect:/login";
        }

        boolean isError = false;
        StringBuilder errorMsg = new StringBuilder();

        AccountEmployeeSalary add = null;

        try {
            add = new AccountEmployeeSalary();
        } catch (IllegalArgumentException e) {
            isError = true;
            errorMsg.append(e.getMessage()).append("\n");
        }

        if (!isError && accountDAO.getAccountByLogin(employeeLogin) != null) {
            isError = true;
            errorMsg.append("account with login '").append(add.account.getLogin()).append("' already exist.").append("\n");
        }

        if (!isError) {
            salaryDAO.add(add.salary);
            employeeDAO.add(add.employee);
            accountDAO.add(add.account);

            return "redirect:/employee/view";
        } else {
            return "redirect:/employee/add?error=" + errorMsg.toString();
        }
    }

    private class AccountEmployeeSalary {
        private String employeeName;
        private String employeeBirthday;
        private String employeeDepartment;
        private String employeeRole;
        private String employeeLogin;
        private String employeePass;
        private String employeeSalary;

        private Salary salary;
        private Employee employee;
        private Account account;

        public AccountEmployeeSalary() {

        }

        private void set() {
            setSalary();
            setEmployee();
            setAccount();
        }

        private void setSalary() {
            try {
                this.salary = new Salary(Integer.parseInt(employeeSalary));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }
        }

        private void setEmployee() {
            try {
                this.employee = new Employee(employeeName, Date.valueOf(employeeBirthday), roleDAO.getByName(employeeRole),
                        departmentDAO.getByName(employeeDepartment), salary);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }
        }

        private void setAccount() {
            try {
                this.account = new Account(employeeLogin, employeePass, employee);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public void setEmployeeBirthday(String employeeBirthday) {
            this.employeeBirthday = employeeBirthday;
        }

        public void setEmployeeDepartmentName(String employeeDepartment) {
            this.employeeDepartment = employeeDepartment;
        }

        public void setEmployeeRoleName(String employeeRole) {
            this.employeeRole = employeeRole;
        }

        public void setEmployeeLogin(String employeeLogin) {
            this.employeeLogin = employeeLogin;
        }

        public void setEmployeePass(String employeePass) {
            this.employeePass = employeePass;
        }

        public void setEmployeeSalary(String employeeSalary) {
            this.employeeSalary = employeeSalary;
        }
    }

    // TODO: delete, update

    @GetMapping("/employee/edit/{id}")
    public String getEmployeeEdit(@CookieValue(value = "login", defaultValue = "") String login,
                                  @PathVariable(value = "id") int id, Model model) {

        model = accountForJSP(login, model);

        if (login.isEmpty() || (Security.getRoleId(login) != Security.RoleId.MANAGER)) {
            return "redirect:/login";
        }

        Employee employee = employeeDAO.getById(id);

        model.addAttribute("departments", departmentDAO.getList());
        model.addAttribute("roles", roleDAO.getList());

        model.addAttribute("employee", employee);
        model.addAttribute("account", accountDAO.getAccountByEmployee(employee));

        return "/employee/edit";
    }

    @PostMapping("/employee/edit")
    public String editEmployee(@CookieValue(value = "login", defaultValue = "") String login,
                               @RequestParam(value = "employeeId") String Id,
                               @RequestParam(value = "currLogin") String currLogin,
                              /* @RequestParam(value = "name") String employeeName,
                               @RequestParam(value = "birthday") String employeeBirthday,
                               @RequestParam(value = "department") String employeeDepartmentName,
                               @RequestParam(value = "role") String employeeRoleName,
                               @RequestParam(value = "login") String employeeLogin,
                               @RequestParam(value = "pass") String employeePass,
                               @RequestParam(value = "salary") String employeeSalary*/
                               @ModelAttribute AccountEmployeeSalary updated) {

        if (login.isEmpty() || !(Security.getRoleId(login) == Security.RoleId.MANAGER)) {
            return "redirect:/login";
        }

        boolean isError = false;
        StringBuilder errorMsg = new StringBuilder();

        int employeeId = Integer.parseInt(Id);

        /*AccountEmployeeSalary updated = null;

        try {
            updated = new AccountEmployeeSalary(employeeName, employeeBirthday, employeeDepartmentName,
                    employeeRoleName, employeeLogin, employeePass, employeeSalary);
        } catch (IllegalArgumentException e) {
            isError = true;
            errorMsg.append(e.getMessage()).append("\n");
        }*/

        /*if (!isError && accountDAO.getAccountByLogin(employeeLogin) != null && !employeeLogin.equals(currLogin)) {
            isError = true;
            errorMsg.append("account with login '").append(updated.account.getLogin()).append("' already exist.").append("\n");
        }*/

        if (!isError) {

            accountDAO.update(accountDAO.getAccountByLogin(updated.account.getLogin()));
            employeeDAO.update(updated.employee);
            salaryDAO.update(updated.salary);

            return "redirect:/employee/view/" + updated.employee.getId();
        } else {
            return "redirect:/error?error=" + errorMsg.toString();
        }
    }

    @GetMapping("/employee/delete")
    public String getEmployeeDelete(@CookieValue(value = "login", defaultValue = "") String login, Model model) {

        model = accountForJSP(login, model);

        return "error";
    }

    @PostMapping("/employee/delete")
    public String addEmployee(@CookieValue(value = "login", defaultValue = "") String login,
                              @RequestParam(value = "employee_id") String id) {

        if (login.isEmpty() || !(Security.getRoleId(login) == Security.RoleId.MANAGER)) {
            return "redirect:/login";
        }

        boolean isError = false;
        StringBuilder errorMsg = new StringBuilder();

        try {

            Employee employee = employeeDAO.getById(Integer.parseInt(id));
            if (employee == null) {
                isError = true;
                errorMsg.append("Employee with ID ").append(id).append(" not found");
            }

            if (!isError && accountDAO.getAccountByEmployee(employee).getLogin().equals(login)) {
                isError = true;
                errorMsg.append("Cant delete current Employee");
            }

            if (!isError && employeeDAO.getCountByRole(roleDAO.getById(1).getId()) == 1) {
                isError = true;
                errorMsg.append("Cant delete last Employee with Role Manager");
            }

            if (!isError) {
                accountDAO.removeByEmployeeId(employee.getId());
                new HolidayDAO().removeByEmployeeId(employee.getId());
                employeeDAO.remove(employee.getId());
                salaryDAO.remove(employee.getSalary().getId());
            }

        } catch (IllegalArgumentException e) {
            isError = true;
            errorMsg.append(e.getMessage());
        }
        if (!isError) {
            return "redirect:/employee/view";
        } else {
            return "redirect:/error?error=" + errorMsg.toString();
        }
    }

}
