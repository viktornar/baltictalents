package lt.baltictalents.lessons.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.baltictalents.lessons.api.model.IsAdmin;

import static lt.baltictalents.lessons.api.utils.BooleanGeneratorUtil.generateRandomBoolean;

@RestController
@RequestMapping("/api")
public class AdminChekerRestController {
    @GetMapping("/is-admin")
    public IsAdmin showIsAdmin() {
        return new IsAdmin(generateRandomBoolean());
    }
}