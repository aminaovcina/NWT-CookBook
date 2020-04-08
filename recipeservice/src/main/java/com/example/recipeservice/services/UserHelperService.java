package com.example.recipeservice.services;

import java.io.Console;

import com.example.recipeservice.helpers.UserDeseralizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserHelperService {
    @Autowired
    private DiscoveryService discoveryService;

    public void getRecipeCreator(Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        String getUserUrl = discoveryService.getUserInstance() + "/user/" + userId.toString();
        UserDeseralizer res = restTemplate.getForObject(getUserUrl, UserDeseralizer.class);
       
    }

}