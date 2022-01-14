package com.forum.controllers;

import com.forum.model.Post;
import com.forum.model.Warn;
import com.forum.model.htmlforms.WarnReasonForm;
import com.forum.services.PostService;
import com.forum.services.UserService;
import com.forum.services.WarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class WarnController {
    private final PostService postService;
    private final WarnService warnService;
    private final UserService userService;

    @Autowired
    public WarnController(PostService postRepository, WarnService warnService, UserService userService) {
        this.postService = postRepository;
        this.warnService = warnService;
        this.userService = userService;
    }

    @GetMapping("/warn")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    public String awardWarn(@RequestParam(name = "p") Long postId, Model model, @ModelAttribute(name = "warnReasonForm") WarnReasonForm warnReasonForm) {
        model.addAttribute("postId", postId);
        model.addAttribute("warnReasonForm", warnReasonForm);
        Optional<Post> postToWarnFor = postService.getById(postId);

        if (postToWarnFor.isPresent()) {
            model.addAttribute("post", postToWarnFor.get());
        } else {
            return "redirect:/wrong-request";
        }
        return "warn";
    }

    @PostMapping("/warn")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    public String awardWarnPost(@RequestParam(name = "p") Long postId, Model model, @ModelAttribute(name = "warnReasonForm") WarnReasonForm warnReasonForm) {
        Optional<Post> postToWarnFor = postService.getById(postId);
        if (postToWarnFor.isPresent()) {
            model.addAttribute("post", postToWarnFor.get());
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDetails details = (UserDetails) principal;
            Warn warn = new Warn();
            warn.setReason(warnReasonForm.getReason());
            warn.setValue(warnReasonForm.getValue());
            warn.setGivenBy(userService.getUserByName(details.getUsername()).get());
            warn.setReceiver(postToWarnFor.get().getAuthor());
            warn.setPost(postToWarnFor.get());
            warnService.save(warn);
            return "redirect:/";
        }
        return "warn";
    }
}
