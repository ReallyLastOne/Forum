package com.forum.controllers;

import com.forum.model.User;
import com.forum.services.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.TemplateEngine;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final UserService userService;
    private final TemplateEngine templateEngine;

    @Autowired
    public RegistrationController(UserService userService, TemplateEngine templateEngine) {
        this.userService = userService;
        this.templateEngine = templateEngine;
    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (userService.getUserByName(user.getName()).isEmpty() && userService.getUserByEmail(user.getEmail()).isEmpty()) {
            userService.saveUser(user);
            model.addAttribute("alert", "Successful register.");
            return "register";
        }
        model.addAttribute("alert", "Unsuccessful register.");

        return "register";
    }
}

