package com.forum.controllers;

import com.forum.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/sections")
public class SectionController {
    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping
    public String getSectionsPage(Model model) {
        model.addAttribute("sections", sectionService.getAllSections());
        return "sections";
    }

    @GetMapping(value = "/{id}")
    public String getSectionPage(@PathVariable long id, Model model) {
        model.addAttribute("section", sectionService.getSection(id));
        return "section";
    }
}
