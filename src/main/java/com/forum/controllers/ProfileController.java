package com.forum.controllers;

import com.forum.model.Conversation;
import com.forum.model.PasswordForm;
import com.forum.model.User;
import com.forum.model.UserInfo;
import com.forum.services.UserMapper;
import com.forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequestMapping("/profile")
@Controller
public class ProfileController {
    private final UserService userService;
    private final UserMapper userMapper;
    private static final List<String> POSSIBLE_ACTIONS = Arrays.asList("editprofile", "editpassword", "pm");
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileController(UserService userService, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getProfile(@RequestParam(name = "do", required = false) String action, @RequestParam(name = "id", required = false) Long pmId, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal.equals("anonymousUser")) return "redirect:/";
        model.addAttribute("view", action);
        UserDetails details = (UserDetails) principal;
        User user = userService.getUserByName(details.getUsername()).get();

        if (action == null || !POSSIBLE_ACTIONS.contains(action)) {
            model.addAttribute("view", "main");
            return "profile";
        } else if ("editprofile".equals(action)) {
            model.addAttribute("userInfo", user.getUserInfo());
        } else if ("editpassword".equals(action)) {
            model.addAttribute("passwordForm", new PasswordForm());
        } else if ("pm".equals(action)) {
            var conversations = user.getConversations();

            if (pmId != null) {
                model.addAttribute("view", "conversation");
                conversations.stream().filter(e -> e.getId().equals(pmId)).findFirst().ifPresent(e -> model.addAttribute("conversation", e));
            } else {
                model.addAttribute("conversations", conversations);
            }
        }

        return "profile";
    }

    @PostMapping
    public String changeProfileData(
            @RequestParam(name = "do", required = true) String action, Model model,
            @ModelAttribute(name = "userInfo") @Valid UserInfo userInfo,
            @ModelAttribute(name = "passwordForm") PasswordForm passwordForm) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.equals("anonymousUser")) return "redirect:/";
        UserDetails details = (UserDetails) principal;
        User user = userService.getUserByName(details.getUsername()).get();

        if ("editprofile".equals(action)) {
            if (userInfo.getBirthDate() != null) {
                user.getUserInfo().setBirthDate(userInfo.getBirthDate());
            }
            user.getUserInfo().setSex(userInfo.getSex());
            user.getUserInfo().setDescription(userInfo.getDescription());

            userService.saveUser(user);
        } else if ("editpassword".equals(action)) {
            if (passwordEncoder.matches(passwordForm.getCurrentPassword(), user.getPassword()) &&
                    passwordForm.getNewPassword().equals(passwordForm.getNewPasswordRepeated())) {
                user.setPassword(passwordEncoder.encode(passwordForm.getNewPassword()));
                userService.saveUser(user);
            }
        }
        model.addAttribute("view", "main");
        return "profile";
    }
}
