package com.forum.services;

import com.forum.model.User;
import com.forum.model.dtos.UserDto;
import com.forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    @Autowired
    private UserRepository userRepository;

    public List<UserDto> usersToUserDTOs(List<User> users) {
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

    public UserDto userToUserDTO(User user) {
        return new UserDto(user);
    }
}
