package com.forum.controllers;

import com.forum.model.Thread;
import com.forum.model.User;
import com.forum.services.ThreadService;
import com.forum.services.UserMapper;
import com.forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final ThreadService threadService;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, ThreadService threadService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.threadService = threadService;
    }

    @GetMapping(value = "/users")
    public String getSingleUser(@RequestParam(value = "id") long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", userMapper.userToUserDTO(user.get()));
            return "user";
        }
        return "wrong-request";
    }

    @GetMapping(value = "/user-threads/{id}")
    public String getUserThreads(@PathVariable(value = "id") long id, Model model) {
        Optional<List<Thread>> threadsOptional = threadService.getUsersThreads(id);
        if (threadsOptional.isPresent()) {
            model.addAttribute("threads", threadsOptional.get());
            return "user-threads";
        }
        return "wrong-request";
    }
}
