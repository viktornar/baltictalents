package com.sd.petclinic.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
    @RequestMapping(path= {"/", "/welcome"})
    String welcome() {
        return "welcome";
    }
}