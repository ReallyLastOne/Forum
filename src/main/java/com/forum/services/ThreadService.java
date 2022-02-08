package com.forum.services;

import com.forum.model.Post;
import com.forum.model.Thread;
import com.forum.model.User;
import com.forum.model.dtos.UserDto;
import com.forum.repositories.ThreadRepository;
import com.forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ThreadService {
    private final ThreadRepository threadRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public ThreadService(ThreadRepository threadRepository, UserRepository userRepository, UserMapper userMapper) {
        this.threadRepository = threadRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Optional<Thread> getThread(long id) {
        return threadRepository.findById(id);
    }


    public Optional<List<Thread>> getUsersThreads(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserDto user = userMapper.userToUserDTO(optionalUser.get());
            return Optional.of(new ArrayList<>(new HashSet<>(
                    user.getPosts().stream().map(Post::getThread).map(e -> e.findMostRecentPost().get()).
                            filter(e -> e.getAuthor().getId() == id).map(Post::getThread).collect(Collectors.toList()))));
        }
        return Optional.empty();
    }

    public void delete(Thread thread) {
        threadRepository.delete(thread);
    }

    public void deleteById(Long id) {
        threadRepository.deleteById(id);
    }

    public void save(Thread thread) {
        threadRepository.save(thread);
    }
}
