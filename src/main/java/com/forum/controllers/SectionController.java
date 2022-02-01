package com.forum.controllers;

import com.forum.model.Section;
import com.forum.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

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
