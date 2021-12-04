package com.forum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class MainPageController {

    @GetMapping("")
    public String getMainPage(Model model, HttpServletRequest request) {
        return "index";
    }
}
