package com.forum.controllers;

import com.forum.model.*;
import com.forum.model.htmlforms.MessageContent;
import com.forum.model.htmlforms.MessageForm;
import com.forum.model.htmlforms.PasswordForm;
import com.forum.services.ConversationService;
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
    private static final List<String> POSSIBLE_ACTIONS = Arrays.asList("editprofile", "editpassword", "pm", "newpm");
    private final PasswordEncoder passwordEncoder;
    private final ConversationService conversationService;

    @Autowired
    public ProfileController(UserService userService, UserMapper userMapper, PasswordEncoder passwordEncoder, ConversationService messageService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.conversationService = messageService;
    }

    @GetMapping
    public String getProfile(
            @RequestParam(name = "do", required = false) String action,
            @RequestParam(name = "id", required = false) Long pmId, Model model,
            @RequestParam(name = "u", required = false) Long uId,
            @ModelAttribute(name = "messageContent") MessageContent messageContent,
            @ModelAttribute(name = "message") MessageForm messageForm) {
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
            var conversations = user.findConversations();

            if (pmId != null) {
                model.addAttribute("view", "conversation");
                model.addAttribute("pmId", pmId);
                conversations.stream().filter(e -> e.getId().equals(pmId)).findFirst().ifPresent(e -> model.addAttribute("conversation", e));
            } else {
                model.addAttribute("conversations", conversations);
            }
        } else if ("newpm".equals(action)) {
            Optional<User> userById = userService.getUserById(uId);
            userById.ifPresent(value -> messageForm.setReceiver(value.getName()));
        }

        return "profile";
    }

    @PostMapping
    public String changeProfileData(
            @RequestParam(name = "do", required = true) String action, Model model,
            @ModelAttribute(name = "userInfo") @Valid UserInfo userInfo,
            @ModelAttribute(name = "passwordForm") PasswordForm passwordForm,
            @RequestParam(name = "id", required = false) Long pmId,
            @ModelAttribute(name = "messageContent") MessageContent messageContent,
            @ModelAttribute(name = "message") MessageForm messageForm) {
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
        } else if ("pm".equals(action)) {
            if (pmId == null) return "redirect:/profile";
            Optional<Conversation> conversation = user.findConversations().stream().filter(e -> e.getId().equals(pmId)).findAny();
            conversation.ifPresent(value -> {
                value.addMessage(user, messageContent.getContent());
                conversationService.save(conversation.get());
            });
        } else if ("newpm".equals(action)) {
            var receiver = userService.getUserByName(messageForm.getReceiver());
            if (receiver.isPresent()) {
                Conversation conversation = new Conversation();
                conversation.setTitle(messageForm.getTitle());
                conversation.addMessage(user, receiver.get(), messageForm.getContent());
                conversationService.save(conversation);
            }
            return "redirect:/profile?do=pm";
        }

        model.addAttribute("view", "main");
        return "profile";
    }
}
