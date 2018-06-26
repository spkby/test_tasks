package company.controller;

import company.DAO.AccountDAO;
import company.model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {

        if (error != null && !error.isEmpty()) {
            model.addAttribute("error", true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute Account acc, HttpServletResponse resp) {

        Account account = new AccountDAO().getAccountByLogin(acc.getLogin());

        if (account != null && account.getPass().equals(acc.getPass())) {
            Cookie cookie = new Cookie("login", account.getLogin());
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            resp.addCookie(cookie);
            return "redirect:/";
        } else {
            return "redirect:/login?error=Invalid login or password";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp) {

        Cookie[] cookies = req.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }

        return "redirect:/";
    }
}
