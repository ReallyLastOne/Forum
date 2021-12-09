package com.forum.controllers;

import com.forum.model.Thread;
import com.forum.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping(value = "/threads")
public class ThreadController {
    private final ThreadService threadService;

    @Autowired
    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
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
}
