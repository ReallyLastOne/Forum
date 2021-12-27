package com.forum.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
public class UserDto {
    private long id;
    private String name;
    private List<Post> posts;
    private LocalDateTime registerDate;
    private boolean banned;
    private Set<Role> roles;
    private UserInfo userInfo;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.posts = user.getPosts();
        this.registerDate = user.getRegisterDate();
        this.banned = user.isBanned();
        this.roles = user.getRoles();
        this.userInfo = user.getUserInfo();
    }
}
