package com.forum.services;

import com.forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final UserRepository userRepository;

    @Autowired
    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
