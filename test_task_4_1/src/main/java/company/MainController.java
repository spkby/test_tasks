package company;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/{name}")
    public String view(@PathVariable(value = "name", required = false) String name, Model model) {
        model.addAttribute("msg", name);
        return "index";
    }

    @GetMapping("/raw")
    @ResponseBody
    public String raw(){
        return "Raw data";
    }
}
