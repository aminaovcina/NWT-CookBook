package com.example.recipeservice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.recipeservice.helpers.UserDeseralizer;
import com.example.recipeservice.models.Role;
import com.example.recipeservice.models.User;

@FeignClient(name="userservice")
public interface UserHelperService{
    public static final String AUTHORIZATION= "Authorization";

    @GetMapping("/user/{id}")
    UserDeseralizer getUserById(@PathVariable("id") Long id);

    @GetMapping("accounts/validate")
    User getAccountValidate(@RequestHeader(AUTHORIZATION) String token);

    @GetMapping("users/role")
    Role getRoleByToken(@RequestHeader(AUTHORIZATION) String token);
}
  