package lt.baltictalents.lesson3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String getWelcome() {
        return "welcome";
    }
}
