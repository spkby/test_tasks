package company.controller;

import company.DAO.AccountDAO;
import company.DAO.EmployeeDAO;
import company.DAO.HolidayDAO;
import company.DAO.StatusDAO;
import company.Security;
import company.Utils;
import company.model.Holiday;
import company.model.Status;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HolidayController extends AbstractController {

    private static AccountDAO accountDAO = new AccountDAO();
    private static HolidayDAO holidayDAO = new HolidayDAO();
    private static StatusDAO statusDAO = new StatusDAO();

    @GetMapping("/holiday")
    public String root() {
        return "redirect:/holiday/list";
    }

    @GetMapping("/holiday/list")
    public String list(@CookieValue(value = "login", defaultValue = "") String login, Model model) {

        model = accountForJSP(login, model);

        if (login.isEmpty()) {
            return "redirect:/login";
        }

        switch (Security.getRoleId(login)) {
            case LEAD:
                model.addAttribute("holidays", holidayDAO.getListByDepartment(accountDAO
                        .getAccountByLogin(login).getEmployee().getDepartment()));
                break;
            case MANAGER:
                model.addAttribute("holidays", holidayDAO.getList());
                break;
            default:
                return "redirect:/holiday/employee/" + accountDAO.getAccountByLogin(login).getEmployee().getId();
        }
        return "holiday/list";
    }

    @GetMapping("/holiday/employee/{id}")
    public String employee(@CookieValue(value = "login", defaultValue = "") String login,
                           @PathVariable(value = "id") int id, Model model) {

        model = accountForJSP(login, model);

        if (login.isEmpty() || !Security.checkLoginToEmployeeId(login, id)) {
            return "redirect:/login";
        }

        model.addAttribute("holidays", holidayDAO.getListByEmployee(new EmployeeDAO().getById(id)));

        return "holiday/list";
    }

    @GetMapping("/holiday/view/")
    public String view(@CookieValue(value = "login", defaultValue = "") String login) {

        if (login.isEmpty()) {
            return "redirect:/login";
        }
        return "redirect:/holiday/employee/" + accountDAO.getAccountByLogin(login).getEmployee().getId();
    }

    @GetMapping("/holiday/view/{id}")
    public String viewById(@CookieValue(value = "login", defaultValue = "") String login,
                           @PathVariable(value = "id") int id, Model model) {
        model = accountForJSP(login, model);

        if (login.isEmpty() || !Security.checkLoginToHolidayIdToView(login, id)) {
            return "redirect:/login";
        }

        model.addAttribute("holiday", holidayDAO.getById(id));

        return "holiday/view";
    }

    @GetMapping("/holiday/add")
    public String getAdd(@CookieValue(value = "login", defaultValue = "") String login, Model model) {

        model = accountForJSP(login, model);

        if (login.isEmpty()) {
            return "redirect:/login";
        }

        return "/holiday/add";
    }

    @PostMapping("/holiday/add")
    public String addEmployee(@CookieValue(value = "login", defaultValue = "") String login,
                              @RequestParam(value = "dateFrom") String dateFrom,
                              @RequestParam(value = "dateTo") String dateTo) {

        if (login.isEmpty()) {
            return "redirect:/login";
        }

        boolean isError = false;
        StringBuilder errorMsg = new StringBuilder();


        Holiday holiday = new Holiday();
        try {

            if (Utils.dateIsMoreThanToday(dateFrom)) {
                isError = true;
                errorMsg.append("Date From before Today");
            }

            if (Utils.date2IsMoreThanDate1(dateFrom, dateTo)) {
                isError = true;
                errorMsg.append("Date To before From");
            }

            if (!isError) {
                holiday.setDateFrom(Utils.getDate(dateFrom));
                holiday.setDateTo(Utils.getDate(dateTo));
                holiday.setStatus(statusDAO.getById(1));
                holiday.setEmployee(accountDAO.getAccountByLogin(login).getEmployee());
            }

        } catch (IllegalArgumentException e) {
            isError = true;
            errorMsg.append(e.getMessage()).append("\n");
        }

        if (!isError) {
            holidayDAO.add(holiday);
            return "redirect:/holiday/list";
        } else {
            return "redirect:/holiday/add?error=" + errorMsg.toString();
        }
    }

    @GetMapping("/holiday/edit/{id}")
    public String getEdit(@CookieValue(value = "login", defaultValue = "") String login,
                          @PathVariable(value = "id") int id, Model model) {

        model = accountForJSP(login, model);

        if (login.isEmpty() || !Security.checkLoginToHolidayId(login, id)) {
            return "redirect:/login";
        }

        model.addAttribute("holiday", holidayDAO.getById(id));

        return "/holiday/edit";
    }

    @PostMapping("/holiday/edit")
    public String editEmployee(@CookieValue(value = "login", defaultValue = "") String login,
                               @RequestParam(value = "dateFrom") String dateFrom,
                               @RequestParam(value = "dateTo") String dateTo,
                               @RequestParam(value = "holiday_id") String id) {

        int holidayId = Integer.parseInt(id);

        if (login.isEmpty() || !Security.checkLoginToHolidayId(login, holidayId)) {
            return "redirect:/login";
        }

        Holiday holiday = null;
        boolean isError = false;
        StringBuilder errorMsg = new StringBuilder();

        try {
            if (Utils.dateIsMoreThanToday(dateFrom)) {
                isError = true;
                errorMsg.append("Date From before Today");
            }

            if (Utils.date2IsMoreThanDate1(dateFrom, dateTo)) {
                isError = true;
                errorMsg.append("Date To before From");
            }

            holiday = holidayDAO.getById(holidayId);
            holiday.setDateFrom(Utils.getDate(dateFrom));
            holiday.setDateTo(Utils.getDate(dateTo));

        } catch (IllegalArgumentException e) {
            isError = true;
            errorMsg.append(e.getMessage()).append("\n");
        }

        if (!isError) {
            holidayDAO.update(holiday);

            return "redirect:/holiday/view/" + holiday.getId();
        } else {
            return "redirect:/holiday/edit/" + holiday.getId() + "?error=" + errorMsg.toString();
        }
    }

    @GetMapping("/holiday/delete")
    public String getDelete(@CookieValue(value = "login", defaultValue = "") String login, Model model) {

        model = accountForJSP(login, model);

        return "error";
    }

    @PostMapping("/holiday/accepted")
    public String accepted(@CookieValue(value = "login", defaultValue = "") String login,
                           @RequestParam(value = "holiday_id") String id) {

        return changeStatus(Integer.parseInt(id), new StatusDAO().getById(2), login);
    }

    private static String changeStatus(int id, Status status, String login) {

        if (login.isEmpty()
                || !(Security.getRoleId(login) == Security.RoleId.MANAGER || Security.getRoleId(login) == Security.RoleId.LEAD)) {
            return "redirect:/login";
        }

        Holiday holiday = holidayDAO.getById(id);
        holiday.setStatus(status);

        holidayDAO.update(holiday);

        return "redirect:/holiday/view/" + id;
    }

    @PostMapping("/holiday/denied")
    public String denied(@CookieValue(value = "login", defaultValue = "") String login,
                         @RequestParam(value = "holiday_id") String id) {

        return changeStatus(Integer.parseInt(id), new StatusDAO().getById(3), login);
    }

    @PostMapping("/holiday/delete")
    public String add(@CookieValue(value = "login", defaultValue = "") String login,
                      @RequestParam(value = "holiday_id") String id) {

        int holidayId = Integer.parseInt(id);

        if (login.isEmpty() || !Security.checkLoginToHolidayId(login, holidayId)) {
            return "redirect:/login";
        }

        boolean isError = false;
        StringBuilder errorMsg = new StringBuilder();

        try {
            holidayDAO.remove(holidayDAO.getById(holidayId).getId());
        } catch (Exception e) {
            isError = true;
            errorMsg.append(e.getMessage()).append("\n");
        }

        if (!isError) {
            return "redirect:/holiday/view/" + holidayId;
        } else {
            return "redirect:/holiday/edit/" + holidayId + "?error=" + errorMsg.toString();
        }
    }

}
