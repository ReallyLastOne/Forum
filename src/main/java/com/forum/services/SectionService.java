package com.forum.services;

import com.forum.model.Section;
import com.forum.repositories.SectionRepository;
import com.forum.repositories.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {
    private final SectionRepository sectionRepository;
    private final ThreadRepository threadRepository;

    @Autowired
    public SectionService(SectionRepository sectionRepository, ThreadRepository threadRepository) {
        this.sectionRepository = sectionRepository;
        this.threadRepository = threadRepository;
    }

    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    public Section getSection(long id) {
        return sectionRepository.getById(id);
    }

    public Section getSection(String name) {
        return sectionRepository.findByName(name);
    }
}
