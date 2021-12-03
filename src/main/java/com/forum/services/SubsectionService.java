package com.forum.services;

import com.forum.model.Section;
import com.forum.model.Subsection;
import com.forum.repositories.SectionRepository;
import com.forum.repositories.SubsectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubsectionService {
    private final SubsectionRepository subsectionRepository;

    @Autowired
    public SubsectionService(SubsectionRepository subsectionRepository) {
        this.subsectionRepository = subsectionRepository;
    }

    public List<Subsection> getAllSubsections() {
        return subsectionRepository.findAll();
    }
}
