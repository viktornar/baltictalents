package lt.baltictalents.lessons910.http.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.val;
import lt.baltictalents.lessons910.service.auth.SecurityService;

@Controller
public class IndexController {
    @Autowired
    SecurityService securityService;

    @GetMapping("/")
    ModelAndView getPage(HttpServletRequest request) {
        val mv = new ModelAndView("welcome");
        return mv;
    }
}