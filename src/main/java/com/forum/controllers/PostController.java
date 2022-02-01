package com.forum.controllers;

import com.forum.model.Post;
import com.forum.model.Thread;
import com.forum.services.PostService;
import com.forum.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class PostController {
    private final PostService postService;
    private final ThreadService threadService;

    @Autowired
    public PostController(PostService postService, ThreadService threadService) {
        this.postService = postService;
        this.threadService = threadService;
    }

    @PostMapping("/posts")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    public String deletePost(@RequestParam(name = "p") Long postId) {
        Optional<Post> post = postService.getById(postId);

        if (post.isPresent()) {
            Thread thread = post.get().getThread();
            if (thread.getPosts().size() == 1) threadService.delete(thread);
            else {
                postService.deleteById(postId);
                thread.removePost(post.get());
                threadService.save(thread);
            }
        }
        return "redirect:/";
    }
}
