package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import com.example.demo.dto.UserDto;
import com.example.demo.errors.exception.TheSameEmailException;
import com.example.demo.errors.exception.UserNotFoundException;
import com.example.demo.feign.RecipeUser;
import com.example.demo.feign.SystemEvents;
import com.example.demo.helper.AuthorizationHelper;
import com.example.demo.models.Account;
import com.example.demo.models.Recipe;
import com.example.demo.models.SystemEvent;
import com.example.demo.models.User;
import com.example.demo.models.UserLogin;
import com.example.demo.models.UserRequest;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.AccountService;
import com.example.demo.services.UserService;
import com.google.api.Authentication;
import com.ibm.watson.assistant.v2.model.MessageResponse;

import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageState;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
  public static final String AUTHORIZATION= "Authorization";

  @Autowired
  UserService userService;

  @Autowired
  RecipeUser recipeUser;

  @Autowired
  AccountRepository aRepository;

  @Autowired
  AuthorizationHelper authorizationhelper;

  private UserRepository applicationUserRepository;
  private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserController(UserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

 

  @GetMapping("/users")
  private List<User> getAllUsers(@RequestHeader(AUTHORIZATION) String token) {
    authorizationhelper.authorize(token);
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
    try {
      userService.save(user);
    }
    catch(Exception ex) {
      throw new TheSameEmailException("E-mail must be unique");
    }
    return user.getId();
  }

  @PutMapping("/user/update/{id}")
  private User putUser(@PathVariable("id") int id, @Valid @RequestBody User userDetails) {
    User user = userService.getUserById(id);
    try {
      user.setFirstName(userDetails.getFirstName());
      user.setLastName(userDetails.getLastName());
      user.setCity(userDetails.getCity());
      user.setGender(userDetails.getGender());
      user.setDate_Of_Birth(userDetails.getDate_Of_Birth());
      user.setActive(userDetails.getActive());
      user.setEmail(userDetails.getEmail());

      userService.save(user);

    } catch (Exception k) {
      throw new UserNotFoundException("User: " + id + " not Found");
    }
     
      return user;
  }

  // komunikacija sa recipe servisom - Projektni zadatak 5

  @GetMapping("user_recipes/{id}")
  public UserDto getCustomerById(@PathVariable Long id) {

    UserDto dto = new UserDto();
    try {
      User user = userService.getUserById(id.intValue());
      
      List<Recipe> recipes = new ArrayList<Recipe>();
      recipes = recipeUser.getRecipesByUser(id);

        if(recipes.size()==0) 
          user.setActive(false);
        else 
          user.setActive(true);
        
        userService.save(user);
      
        BeanUtils.copyProperties(user, dto);
        dto.setRecipes(recipes);

    } catch (Exception k) {
      throw new UserNotFoundException("User: " + id + " not Found");
    }
     
      return dto;
    }
    //api za registraciju

    @PostMapping("/register")
  public User registerUser(@Valid @RequestBody UserRequest userRequest) {
    User user = new User();
    try {
      user.setFirstName(userRequest.getFirstName());
      user.setLastName(userRequest.getLastName());
      user.setCity(userRequest.getCity());
      user.setGender(userRequest.getGender());
      user.setDate_Of_Birth(userRequest.getDateOfBirth());
      user.setActive(userRequest.getActive());
      user.setEmail(userRequest.getEmail());

      if(userRequest.getPassword().equals(userRequest.getPasswordConfirm())) {
        //kodiraj password u token i sacuvaj u tabelu User

        user.setToken(bCryptPasswordEncoder.encode(userRequest.getPassword()));

        userService.save(user);
      }

    } catch (Exception k) {
      k.printStackTrace();
      throw new UserNotFoundException("User: " +  user.getFirstName() + " not Found");
    }
     
      return user;
  }
  
   //api za login

   @PostMapping("/login")
   public Account loginUser(@Valid @RequestBody UserLogin loginRequest) {
     Account account = new Account();
     try {
        String userName = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        User user = getUserFromTable(userName);
       //provjera da li taj token postoji u tabeli User
      if(bCryptPasswordEncoder.matches(password, user.getToken())) {
        account.setToken(user.getToken());
        account.setUser(user);
        aRepository.save(account);
      }

     } catch (Exception k) {
       k.printStackTrace();
       throw new UserNotFoundException("User: " +  account.getId() + " not Found");
     }
      
       return account;
   }

   

   public User getUserFromTable(String userName) {

    List<User> users = userService.getAllUsers();
    for(int i=0; i<users.size(); i++) {
      if(users.get(i).getEmail().equals(userName))
       return users.get(i);
    }
    return null;
  }

  

}