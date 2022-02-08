package com.forum.controllers;

import com.forum.model.dtos.PostDto;
import com.forum.model.dtos.SectionMainDto;
import com.forum.services.PostService;
import com.forum.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class MainPageController {
    private final SectionService sectionService;
    private final PostService postService;

    @Autowired
    public MainPageController(SectionService sectionService, PostService postService) {
        this.sectionService = sectionService;
        this.postService = postService;
    }

    @GetMapping(value = "")
    public String getSectionsDto(Model model) {
        System.out.println("cotrnoller");
        List<SectionMainDto> sectionsMainDto = sectionService.getAllSectionsMainDto();

        for (SectionMainDto section : sectionsMainDto) {
            List<PostDto> posts = postService.getAllPostsFromSection(section.getId(), PageRequest.of(0, 1));
            // retrieving only first PostDto - most recent, since list is sorted
            if (posts.size() != 0) {
                // set existing Post to section. Wil be used in view.
                postService.getById(posts.get(0).getId()).ifPresent(section::setMostRecentPost);
            }
        }

        model.addAttribute("sections", sectionsMainDto);
        return "index";
    }
}
