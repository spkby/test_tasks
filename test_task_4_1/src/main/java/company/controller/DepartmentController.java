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

    @GetMapping("/department/view")
    public String getDepartmentView(@CookieValue(value = "login", defaultValue = "") String login, Model model) {

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
    public String getDepartmentMyView(@CookieValue(value = "login", defaultValue = "") String login, Model model) {

        model = accountForJSP(login, model);

        if (login.isEmpty()) {
            return "redirect:/login";
        }
        int id = new AccountDAO().getAccountByLogin(login).getEmployee().getDepartment().getId();

        return getDepartmentByIdView(login, id, model);
    }

    @GetMapping("/department/view/{id}")
    public String getDepartmentByIdView(@CookieValue(value = "login", defaultValue = "") String login, @PathVariable(value = "id") int id, Model model) {

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
