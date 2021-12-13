package com.forum.controllers;

import com.forum.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class MainPageController {
    private final SectionService sectionService;

    @Autowired
    public MainPageController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("")
    public String getMainPage(Model model) {
        model.addAttribute("sections", sectionService.getAllSections());
        model.addAttribute("service", sectionService);

        return "index";
    }
}
