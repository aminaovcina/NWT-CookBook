package com.example.demo.controllers;

import com.example.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

}