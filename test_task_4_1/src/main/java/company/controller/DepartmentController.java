package company.controller;

import company.DAO.AccountDAO;
import company.DAO.DepartmentDAO;
import company.DAO.EmployeeDAO;
import company.Security;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DepartmentController extends AbstractController {

    @GetMapping("/department")
    public String root() {
        return "redirect:/department/list";
    }

    @GetMapping("/department/list")
    public String list(@CookieValue(value = "login", defaultValue = "") String login, Model model) {

        model = accountForJSP(login, model);

        if (login.isEmpty()) {
            return "redirect:/login";
        }

        if (Security.getRoleId(login) == Security.RoleId.MANAGER) {
            model.addAttribute("departments", new DepartmentDAO().getList());
            return "department/list";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/department/view/")
    public String view(@CookieValue(value = "login", defaultValue = "") String login, Model model) {

        if (login.isEmpty()) {
            return "redirect:/login";
        }

        return "redirect:/department/view/" + new AccountDAO().getAccountByLogin(login).getEmployee().getDepartment().getId();
    }

    @GetMapping("/department/view/{id}")
    public String viewById(@CookieValue(value = "login", defaultValue = "") String login, @PathVariable(value = "id") int id, Model model) {

        model = accountForJSP(login, model);

        if (login.isEmpty() || !Security.checkLoginToDepartmentId(login, id)) {
            return "redirect:/login";
        }

        if (Security.getRoleId(login) == Security.RoleId.MANAGER || Security.getRoleId(login) == Security.RoleId.LEAD) {
            model.addAttribute("employees", new EmployeeDAO().getListByDepartment(id));
            return "employee/list";
        } else {
            return "redirect:/login";
        }
    }
}
