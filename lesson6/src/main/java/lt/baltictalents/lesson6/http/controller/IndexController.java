package lt.baltictalents.lesson6.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    String getPage() {
        System.out.println("Hello world!!!");
        return "welcome";
    }
}