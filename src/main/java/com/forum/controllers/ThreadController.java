package com.forum.controllers;

import com.forum.model.Post;
import com.forum.model.Thread;
import com.forum.model.User;
import com.forum.services.PostService;
import com.forum.services.ThreadService;
import com.forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping(value = "/threads")
public class ThreadController {
    private final ThreadService threadService;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public ThreadController(ThreadService threadService, UserService userService, PostService postService) {
        this.threadService = threadService;
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping
    public String getSingleThread(@RequestParam long id, Model model) {
        Optional<Thread> thread = threadService.getThread(id);
        if (thread.isPresent()) {
            System.out.println(thread.get());

            model.addAttribute("thread", thread.get());
            return "thread";
        }
        return "wrong-request";
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public String addPostToSingleThread(@RequestParam long id, Model model, String content) {
        Optional<Thread> thread = threadService.getThread(id);
        if (thread.isPresent()) {
            String username;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }

            Optional<User> user = userService.getUserByName(username);
            model.addAttribute("thread", thread.get());
            Post post = postService.savePost(new Post(content, thread.get(), user.get(), LocalDateTime.now()));
            thread.get().addPost(post);
            return "redirect:/threads?id=" + id;
        }
        return "wrong-request";
    }
}
