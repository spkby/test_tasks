package company.controller;

import company.DAO.*;
import company.Security;
import company.Utils;
import company.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController extends AbstractController {

    private static AccountDAO accountDAO = new AccountDAO();
    private static DepartmentDAO departmentDAO = new DepartmentDAO();
    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    private static RoleDAO roleDAO = new RoleDAO();
    private static SalaryDAO salaryDAO = new SalaryDAO();

    @GetMapping("/employee")
    public String root() {
        return "redirect:/employee/list";
    }

    @GetMapping("/employee/list")
    public String list(@CookieValue(value = "login", defaultValue = "") String login, Model model) {

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
            return "redirect:/employee/view/" + accountDAO.getAccountByLogin(login).getEmployee().getId();
        }
    }

    @GetMapping("/employee/view/")
    public String view(@CookieValue(value = "login", defaultValue = "") String login, Model model) {

        if (login.isEmpty()) {
            return "redirect:/login";
        }
        return "redirect:/employee/view/" + accountDAO.getAccountByLogin(login).getEmployee().getId();
    }

    @GetMapping("/employee/view/{id}")
    public String viewById(@CookieValue(value = "login", defaultValue = "") String login,
                           @PathVariable(value = "id") int id, Model model) {
        model = accountForJSP(login, model);

        if (login.isEmpty() || !Security.checkLoginToEmployeeId(login, id)) {
            return "redirect:/login";
        }

        model.addAttribute("account", accountDAO.getAccountByEmployee(employeeDAO.getById(id)));
        return "employee/view";
    }

    @GetMapping("/employee/add")
    public String add(@CookieValue(value = "login", defaultValue = "") String login, Model model) {

        model = accountForJSP(login, model);

        if (login.isEmpty() || (Security.getRoleId(login) != Security.RoleId.MANAGER)) {
            return "redirect:/login";
        }

        model.addAttribute("departments", departmentDAO.getList());
        model.addAttribute("roles", roleDAO.getList());

        return "/employee/add";
    }

    @PostMapping("/employee/add")
    public String add(@CookieValue(value = "login", defaultValue = "") String login,
                      @ModelAttribute AccountEmployeeSalary employeeAdd) {

        if (login.isEmpty() || !(Security.getRoleId(login) == Security.RoleId.MANAGER)) {
            return "redirect:/login";
        }

        boolean isError = false;
        StringBuilder errorMsg = new StringBuilder();


        try {
            employeeAdd.create();
        } catch (IllegalArgumentException e) {
            isError = true;
            errorMsg.append(e.getMessage()).append("\n");
        }

        if (!isError && accountDAO.loginIsExist(employeeAdd.account.getLogin())) {
            isError = true;
            errorMsg.append("account with login '").append(employeeAdd.account.getLogin()).append("' already exist.").append("\n");
        }

        if (!isError) {
            salaryDAO.add(employeeAdd.salary);
            employeeDAO.add(employeeAdd.employee);
            accountDAO.add(employeeAdd.account);

            return "redirect:/employee/list";
        } else {
            return "redirect:/employee/add?error=" + errorMsg.toString();
        }
    }

    @GetMapping("/employee/edit/{id}")
    public String edit(@CookieValue(value = "login", defaultValue = "") String login,
                       @PathVariable(value = "id") int id, Model model) {

        model = accountForJSP(login, model);

        if (login.isEmpty() || (Security.getRoleId(login) != Security.RoleId.MANAGER)) {
            return "redirect:/login";
        }

        model.addAttribute("departments", departmentDAO.getList());
        model.addAttribute("roles", roleDAO.getList());

        //model.addAttribute("employee", employee);
        model.addAttribute("account", accountDAO.getAccountByEmployee(employeeDAO.getById(id)));

        return "/employee/edit";
    }

    @PostMapping("/employee/edit")
    public String edit(@CookieValue(value = "login", defaultValue = "") String login,
                       @RequestParam(value = "currLogin") String currLogin,
                       @ModelAttribute AccountEmployeeSalary employeeEdit) {

        if (login.isEmpty() || !(Security.getRoleId(login) == Security.RoleId.MANAGER)) {
            return "redirect:/login";
        }

        boolean isError = false;
        StringBuilder errorMsg = new StringBuilder();

        try {
            employeeEdit.get();
        } catch (IllegalArgumentException e) {
            isError = true;
            errorMsg.append(e.getMessage()).append("\n");
        }

        if (!isError && accountDAO.loginIsExist(employeeEdit.account.getLogin())) {
            if (!employeeEdit.account.getLogin().equals(currLogin)) {
                isError = true;
                errorMsg.append("account with login '").append(employeeEdit.account.getLogin())
                        .append("' already exist.").append("\n");
            }
        }

        if (!isError) {
            accountDAO.update(employeeEdit.account);
            employeeDAO.update(employeeEdit.employee);
            salaryDAO.update(employeeEdit.salary);

            return "redirect:/employee/view/" + employeeEdit.employee.getId();
        } else {
            return "redirect:/employee/edit/" + employeeEdit.employee.getId() + "?error=" + errorMsg.toString();
        }
    }

    @GetMapping("/employee/delete")
    public String delete(@CookieValue(value = "login", defaultValue = "") String login, Model model) {

        model = accountForJSP(login, model);

        return "error";
    }

    @PostMapping("/employee/delete")
    public String delete(@CookieValue(value = "login", defaultValue = "") String login,
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
            return "redirect:/employee/list";
        } else {
            return "redirect:/error?error=" + errorMsg.toString();
        }
    }

    private class AccountEmployeeSalary {
        private String employeeId;
        private String employeeName;
        private String employeeBirthday;
        private String employeeDepartment;
        private String employeeRole;
        private String employeeLogin;
        private String employeePass;
        private String employeeSalary;

        private int id;

        private Salary salary;
        private Employee employee;
        private Account account;

        public AccountEmployeeSalary() {
        }

        private void create() {
            try {
                salary = new Salary(Integer.parseInt(employeeSalary));

                employee = new Employee(employeeName, Utils.getDate(employeeBirthday), roleDAO.getByName(employeeRole),
                        departmentDAO.getByName(employeeDepartment), salary);

                account = new Account(employeeLogin, employeePass, employee);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }

        private void get() {
            try {
                id = Integer.parseInt(employeeId);

                employee = employeeDAO.getById(id);

                salary = employee.getSalary();
                salary.setQuantity(Integer.parseInt(employeeSalary));

                employee.setBirthday(Utils.getDate(employeeBirthday));
                employee.setName(employeeName);
                employee.setDepartment(departmentDAO.getByName(employeeDepartment));
                employee.setRole(roleDAO.getByName(employeeRole));

                account = accountDAO.getAccountByEmployee(employee);
                account.setLogin(employeeLogin);
                account.setPass(employeePass);

            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }

        public void setEmployeeId(String employeeId) {
            this.employeeId = employeeId.trim();
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName.trim();
        }

        public void setEmployeeBirthday(String employeeBirthday) {
            this.employeeBirthday = employeeBirthday.trim();
        }

        public void setEmployeeDepartment(String employeeDepartment) {
            this.employeeDepartment = employeeDepartment.trim();
        }

        public void setEmployeeRole(String employeeRole) {
            this.employeeRole = employeeRole.trim();
        }

        public void setEmployeeLogin(String employeeLogin) {
            this.employeeLogin = employeeLogin.trim();
        }

        public void setEmployeePass(String employeePass) {
            this.employeePass = employeePass.trim();
        }

        public void setEmployeeSalary(String employeeSalary) {
            this.employeeSalary = employeeSalary.trim();
        }
    }

}
