package com.forum.services;

import com.forum.model.Thread;
import com.forum.repositories.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreadService {
    private final ThreadRepository threadRepository;

    @Autowired
    public ThreadService(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    public Thread getThread(long id) {
        return threadRepository.getById(id);
    }
}
