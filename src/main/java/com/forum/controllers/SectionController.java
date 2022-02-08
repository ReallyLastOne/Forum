package com.forum.controllers;

import com.forum.model.Section;
import com.forum.model.dtos.PostDto;
import com.forum.model.dtos.SectionMainDto;
import com.forum.services.PostService;
import com.forum.services.SectionService;
import com.forum.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/sections")
public class SectionController {
    private final SectionService sectionService;
    private final ThreadService threadService;
    private final PostService postService;

    @Autowired
    public SectionController(SectionService sectionService, ThreadService threadService, PostService postService) {
        this.sectionService = sectionService;
        this.threadService = threadService;
        this.postService = postService;
    }

    @GetMapping
    public String getSectionsPage(Model model) {
        model.addAttribute("sections", sectionService.getAllSections());
        return "sections";
    }

    @GetMapping(value = "/{name}")
    public String getSectionPage(@PathVariable String name, Model model) {
        Optional<Section> sectionOptional = sectionService.getSection(name);
        if (sectionOptional.isPresent()) {
            model.addAttribute("section", sectionOptional.get());
            return "section";
        }
        return "wrong-request";
    }
}
