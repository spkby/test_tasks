package company.controller;

import company.DAO.AccountDAO;
import company.DAO.EmployeeDAO;
import company.Security;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EmployeeController extends AbstractController {

    @GetMapping("/employee/view")
    public String getEmployeeView(@CookieValue(value = "login", defaultValue = "") String login, Model model) {

        model = accountForJSP(login, model);

        if (login.isEmpty()) {
            return "redirect:/login";
        }

        if (Security.getRoleId(login) == Security.RoleId.MANAGER) {
            model.addAttribute("employees", new EmployeeDAO().getList());
            return "employees";
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
        int id = new AccountDAO().getAccountByLogin(login).getEmployee().getId();
        return getEmployeeByIdView(login, id, model);
    }

    @GetMapping("/employee/view/{id}")
    public String getEmployeeByIdView(@CookieValue(value = "login", defaultValue = "") String login,
                                      @PathVariable(value = "id") int id, Model model) {
        model = accountForJSP(login, model);


        if (login.isEmpty() || !Security.checkLoginToEmployeeId(login, id)) {
            return "redirect:/login";
        }

        model.addAttribute("employee", new EmployeeDAO().getById(id));
        return "employee";
    }
}
