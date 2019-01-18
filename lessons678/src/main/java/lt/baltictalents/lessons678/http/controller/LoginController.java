package lt.baltictalents.lessons678.http.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.val;
import lt.baltictalents.lessons678.service.auth.SecurityService;

@Controller
public class LoginController {
    @Autowired
    SecurityService securityService;

    @RequestMapping(value= "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String getRegister(
        @RequestParam(value = "error", required = false) String error,
        Model model
    ) {
        val username = securityService.findLoggedInUsername();

        if (username != null) {
            return "redirect:/";
        }
        
        if (error != null) {
            model.addAttribute("loginError", true);
        } else {
            model.addAttribute("loginError", false);
        }

        return "login";
    }
}