package com.example.demo.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import com.example.demo.dto.UserDto;
import com.example.demo.errors.exception.UserNotFoundException;
import com.example.demo.feign.RecipeUser;
import com.example.demo.models.Gender;
import com.example.demo.models.Recipe;
import com.example.demo.models.User;
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
    public UserDto getCustomerById(@PathVariable Long id){

     /* List<Recipe> recipes = recipeUser.getRecipes(id);

      return recipes;*/
      User user = new User("azz", "ibr",  Gender.female, null, "saa", "azzzz@bb.com");
      List<Recipe> recipes = recipeUser.getRecipesByUser(id);
      UserDto dto = new UserDto();
      BeanUtils.copyProperties(user, dto);
      dto.setRecipes(recipes);


      //Product pr1 = productClient.getProductById("PRD1");
      //Product pr2 = productClient.create(products.get(0));
      //List<Product> pr3 = productClient.listProducts();
      return dto;
    }
    /*@GetMapping("/test")
    private List<User> test() {
      List<Recipe> recipes = recipeUser.getRecipesByUser(1)
        return userService.getAllUsers();
    }*/

}