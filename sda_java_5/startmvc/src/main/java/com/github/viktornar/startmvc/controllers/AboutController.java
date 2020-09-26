package com.github.viktornar.startmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
    @GetMapping("/about")
    String getAboutPage() {
        return "about";
    }
}
