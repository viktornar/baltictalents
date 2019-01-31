package lt.lessons.baltictalents.controller;

import lombok.val;
import lt.lessons.baltictalents.model.User;
import lt.lessons.baltictalents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping({ "/users", "/users/" })
    public String getUsers(Model model) {
        val users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping({ "/api/users", "/api/users/" })
    public @ResponseBody List<User> getUsers() {
        return userRepository.findAll();
    }
}
