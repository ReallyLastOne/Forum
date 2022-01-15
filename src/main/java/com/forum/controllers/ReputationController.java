package com.forum.controllers;

import com.forum.model.Post;
import com.forum.model.Reputation;
import com.forum.model.User;
import com.forum.services.PostService;
import com.forum.services.ReputationService;
import com.forum.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class ReputationController {
    private final ReputationService reputationService;
    private final PostService postService;
    private final UserService userService;

    public ReputationController(ReputationService reputationService, PostService postService, UserService userService) {
        this.reputationService = reputationService;
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/grant-reput")
    @PreAuthorize("isAuthenticated()")
    public String grantReputation(@RequestParam(name = "p") long postId) {
        String username;
        Optional<Post> postToAwardFor = postService.getById(postId);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        if (postToAwardFor.isPresent()) {
            User userGivingReput = userService.getUserByName(username).get();

            if (!reputationService.hasUserGrantedReputToPost(userGivingReput.getId(), postToAwardFor.get().getId())) {
                Reputation reputation = new Reputation(1, postToAwardFor.get().getAuthor(), userGivingReput.getId(), postToAwardFor.get().getId(), LocalDateTime.now().toString());
                reputationService.save(reputation);
                return "redirect:/";
            }
        }
        return "wrong-request";
    }
}
