package com.forum.controllers;

import com.forum.model.Role;
import com.forum.model.User;
import com.forum.repositories.RoleRepository;
import com.forum.services.UserMapper;
import com.forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/register")
public class RegistrationController {
    private final UserService userService;
    private final TemplateEngine templateEngine;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public RegistrationController(UserService userService, TemplateEngine templateEngine, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userService = userService;
        this.templateEngine = templateEngine;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @GetMapping
    @PreAuthorize("!isAuthenticated()")
    public String register(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping
    @PreAuthorize("!isAuthenticated()")
    public String register(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (userService.getUserByName(user.getName()).isEmpty() && userService.getUserByEmail(user.getEmail()).isEmpty()) {
            user.initialize();
            Role role = roleRepository.findByCode("user");
            user.addRole(role);
            model.addAttribute("user", user);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
            model.addAttribute("alert", "Successful register.");
            return "register";
        }
        model.addAttribute("alert", "Unsuccessful register.");

        return "register";
    }
}

