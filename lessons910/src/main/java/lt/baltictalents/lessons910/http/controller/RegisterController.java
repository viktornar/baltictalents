package lt.baltictalents.lessons910.http.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lt.baltictalents.lessons910.model.User;
import lt.baltictalents.lessons910.service.auth.UserService;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register") 
    public String postRegister(
        @ModelAttribute("user") @Valid User user, 
        BindingResult result,
        Model model
    ) {
        if (result.hasErrors()) {
            return "register";
        }


        userService.save(user);
        return "redirect:login";
    }

}