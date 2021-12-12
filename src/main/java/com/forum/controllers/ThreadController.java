package com.forum.controllers;

import com.forum.model.Post;
import com.forum.model.Thread;
import com.forum.model.User;
import com.forum.services.PostService;
import com.forum.services.ThreadService;
import com.forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            model.addAttribute("thread", thread.get());
            return "thread";
        }
        return "wrongThread";
    }

    @PostMapping
    public String addPostToSingleThread(@RequestParam long id, Model model, String content) {
        System.out.println("add post to single thread");
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
            Post post = postService.savePost(new Post(0L, content, thread.get(), user.get()));
            thread.get().addPost(post);
            return "redirect:/threads?id=" + id;
        }
        return "wrongThread";
    }
}
