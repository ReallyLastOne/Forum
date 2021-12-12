package com.forum.services;

import com.forum.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleService {
    private final RoleRepository roleRepository;


    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
