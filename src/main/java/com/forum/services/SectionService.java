package com.forum.services;

import com.forum.model.Post;
import com.forum.model.Section;
import com.forum.model.Thread;
import com.forum.repositories.SectionRepository;
import com.forum.repositories.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

    public Optional<Section> getSection(long id) {
        return sectionRepository.findById(id);
    }

    public Optional<Section> getSection(String name) {
        return Optional.of(sectionRepository.findByName(name));
    }

    public Optional<Thread> getLastActiveThread(long sectionId) {
        Optional<Section> section = sectionRepository.findById(sectionId);
        List<Thread> threads = section.get().getThreads();

        Comparator<LocalDateTime> localDateTimeComparator = LocalDateTime::compareTo;

        Optional<Thread> newestThread = threads.stream().map(Thread::getPosts).flatMap(Collection::stream)
                .max(Comparator.comparing(Post::getCreationDate)).map(Post::getThread);
        return newestThread;
    }
}
