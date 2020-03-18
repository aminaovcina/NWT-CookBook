package com.example.recipeservice.controllers;

import com.example.recipeservice.repositories.PostInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("post")

@RestController
public class PostController {
    @Autowired
    private PostInterface postRepository;

}