package com.example.demo.controllers;

import com.example.demo.repositories.UserInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")

@RestController
public class UserController {
    @Autowired
    private UserInterface userRepository;

}