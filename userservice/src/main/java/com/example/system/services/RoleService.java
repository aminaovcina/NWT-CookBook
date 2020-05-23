package com.example.system.services;

import com.example.system.models.Role;
import com.example.system.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role getRoleById(int id) {
        return roleRepository.findById(id).get();
    }
}