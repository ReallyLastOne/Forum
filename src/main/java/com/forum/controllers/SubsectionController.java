package com.forum.controllers;

import com.forum.model.Section;
import com.forum.model.Subsection;
import com.forum.repositories.SectionRepository;
import com.forum.repositories.SubsectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SubsectionController {
    private final SubsectionRepository subsectionRepository;

    @Autowired
    public SubsectionController(SubsectionRepository subsectionRepository) {
        this.subsectionRepository = subsectionRepository;
    }

    public List<Subsection> getAllSubsections() {
        return subsectionRepository.findAll();
    }
}
