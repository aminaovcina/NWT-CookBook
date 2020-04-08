package com.example.demo.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import com.example.demo.errors.exception.UserNotFoundException;
import com.example.demo.feign.RecipeClient;
import com.example.demo.models.Recipe;
import com.example.demo.models.User;
import com.example.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RecipeClient recipeClient;

    @GetMapping("/users")
    private List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    private User getUserById(@PathVariable("id") int id) {
        User user = null;
        try 
        {
           user = userService.getUserById(id);
        } catch (NoSuchElementException k)
        {
          throw new UserNotFoundException("User: "+ id + " not Found" );
        }
        return user;
    }

    @DeleteMapping("/user/delete/{id}")
    private void deleteUser(@PathVariable("id") int id) {
        try 
        {
          userService.delete(id);
        } catch (Exception k)
        {
          throw new UserNotFoundException("User: "+ id + " not Found" );
        }
    }

    @PostMapping("/user/save")
    private int saveUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return user.getId();
    }

    //komunikacija sa recipeservisom

   @GetMapping("user_recipes/{id}")
    public List<Recipe> getCustomerById(@PathVariable String id){

      List<Recipe> recipes = recipeClient.getRecipes();

      return recipes;
    }

}