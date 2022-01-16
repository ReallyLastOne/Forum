package com.forum.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginController {

    @GetMapping("/login")
    @PreAuthorize("!isAuthenticated()")
    public String login(HttpServletRequest request, Model model) {
        Object logout = model.asMap().get("logout");
        model.addAttribute("logout", logout);
        return "login";
    }

    @PostMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public String logout(RedirectAttributes attrs, HttpServletRequest request) {
        new SecurityContextLogoutHandler().logout(request, null, null);
        attrs.addFlashAttribute("logout", true);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
