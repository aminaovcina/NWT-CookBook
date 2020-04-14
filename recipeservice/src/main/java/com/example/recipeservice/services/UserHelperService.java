package com.example.recipeservice.services;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.recipeservice.helpers.UserDeseralizer;

@FeignClient(name="userservice")
public interface UserHelperService{

    @GetMapping("/user/{id}")
    UserDeseralizer getUserById(@PathVariable("id") Long id);

}
  