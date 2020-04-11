package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.example.demo.dto.UserDto;
import com.example.demo.errors.exception.UserNotFoundException;
import com.example.demo.feign.RecipeUser;
import com.example.demo.models.Recipe;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
  @Autowired
  UserService userService;

  @Autowired
  RecipeUser recipeUser;

  @Autowired
  UserRepository uRepository;

  @GetMapping("/users")
  private List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/user/{id}")
  private User getUserById(@PathVariable("id") int id) {
    User user = null;
    try {
      user = userService.getUserById(id);
    } catch (NoSuchElementException k) {
      throw new UserNotFoundException("User: " + id + " not Found");
    }
    return user;
  }

  @DeleteMapping("/user/delete/{id}")
  private void deleteUser(@PathVariable("id") int id) {
    try {
      userService.delete(id);
    } catch (Exception k) {
      throw new UserNotFoundException("User: " + id + " not Found");
    }
  }

  @PostMapping("/user/save")
  private int saveUser(@RequestBody User user) {
    userService.saveOrUpdate(user);
    return user.getId();
  }

  // komunikacija sa recipeservisom

  @GetMapping("user_recipes/{id}")
  public UserDto getCustomerById(@PathVariable Long id) {
    UserDto dto = new UserDto();
    Optional<User> user = uRepository.findById(id.intValue());
     
     List<Recipe> recipes = new ArrayList<Recipe>();
     recipes = recipeUser.getRecipesByUser(id);

      if(recipes.size()==0) 
        user.get().setActive(false);
      else 
        user.get().setActive(true);
       
      uRepository.save(user.get());
     
      BeanUtils.copyProperties(user.get(), dto);
      dto.setRecipes(recipes);
     
     
      return dto;
    }
   
}