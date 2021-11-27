package com.forum.controllers;

import com.forum.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {
    private final RegisterService registerService;

    @Autowired
    public RegistrationController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("name");
        return "register";
    }
}
