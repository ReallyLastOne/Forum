package com.forum.controllers;

import com.forum.model.Post;
import com.forum.model.Section;
import com.forum.model.Thread;
import com.forum.model.User;
import com.forum.model.htmlforms.ThreadForm;
import com.forum.services.PostService;
import com.forum.services.SectionService;
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
public class ThreadController {
    private final ThreadService threadService;
    private final UserService userService;
    private final PostService postService;
    private final SectionService sectionService;

    @Autowired
    public ThreadController(ThreadService threadService, UserService userService, PostService postService, SectionService sectionService) {
        this.threadService = threadService;
        this.userService = userService;
        this.postService = postService;
        this.sectionService = sectionService;
    }

    @GetMapping(value = "/threads")
    public String getSingleThread(@RequestParam long id, Model model) {
        Optional<Thread> thread = threadService.getThread(id);
        if (thread.isPresent()) {

            model.addAttribute("thread", thread.get());
            return "thread";
        }
        return "wrong-request";
    }

    @PostMapping(value = "/threads")
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
            Post post = new Post(content, user.get(), LocalDateTime.now());
            thread.get().addPost(post);
            threadService.save(thread.get());

            return "redirect:/threads?id=" + id;
        }
        return "wrong-request";
    }


    @GetMapping("/newthread")
    @PreAuthorize("isAuthenticated()")
    public String createNewThread(Model model, @RequestParam(value = "section") Long sectionId, @ModelAttribute("threadForm") ThreadForm threadForm) {
        model.addAttribute("sectionId", sectionId);
        Optional<Section> sectionOptional = sectionService.getSection(sectionId);
        if (sectionOptional.isPresent()) {
            return "new-thread";
        }
        return "wrong-request";
    }

    @PostMapping("/newthread")
    @PreAuthorize("isAuthenticated()")
    public String createNewThreadPost(Model model, @RequestParam(value = "section") Long sectionId, @ModelAttribute("threadForm") ThreadForm threadForm) {
        model.addAttribute("sectionId", sectionId);
        Optional<Section> sectionOptional = sectionService.getSection(sectionId);
        if (sectionOptional.isPresent()) {
            String username;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }

            User user = userService.getUserByName(username).get();

            LocalDateTime now = LocalDateTime.now();
            Thread thread = new Thread(threadForm.getTitle());
            Post post = new Post(threadForm.getContent(), user, now);
            thread.addPost(post);
            sectionOptional.get().addThread(thread);
            threadService.save(thread);
            return "redirect:/";
        }

        return "wrong-request";
    }
}
