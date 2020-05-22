package com.example.system.controllers;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.example.system.dto.UserDto;
import com.example.system.errors.exception.AuthenticationException;
import com.example.system.errors.exception.DontHavePrivilegedException;
import com.example.system.errors.exception.TheSameEmailException;
import com.example.system.errors.exception.UserNotFoundException;
import com.example.system.errors.exception.WrongPasswordException;
import com.example.system.feign.RecipeUser;
import com.example.system.helper.AuthorizationHelper;
import com.example.system.models.Account;
import com.example.system.models.Recipe;
import com.example.system.models.User;
import com.example.system.models.UserLogin;
import com.example.system.models.UserRegister;
import com.example.system.repositories.AccountRepository;
import com.example.system.repositories.UserRepository;
import com.example.system.services.UserService;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
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



  @Autowired
    private AmqpTemplate amqpTemplate;   
  


  private UserRepository applicationUserRepository;
  private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserController(UserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

 

  @GetMapping("/users")
  public List<User> getAllUsers(@RequestHeader(AUTHORIZATION) String token) {
    authorizationhelper.authorize(token);
    return userService.getAllUsers();
  }

  @GetMapping("/users/{id}")
  public User getUserById(@RequestHeader(AUTHORIZATION) String token, @PathVariable("id") int id) {
    authorizationhelper.authorize(token);
    User user = null;
    try {
      user = userService.getUserById(id);
    } catch (NoSuchElementException k) {
      throw new UserNotFoundException("User: " + id + " not Found");
    }
    return user;
  }

  @DeleteMapping("/users/delete/{id}")
  public void deleteUser(@RequestHeader(AUTHORIZATION) String token,@PathVariable("id") int id) {
    authorizationhelper.authorize(token);
   

    //provjera da li je user privilegovan


    User user = userService.getUserById(id);
    if(user.getRole().getRoleId()==1) {
      userService.delete(id);
    } 
    else throw new DontHavePrivilegedException(user.getEmail() + " is not privilaged");
  }

  @PostMapping("/users/save")
  public int saveUser(@RequestHeader(AUTHORIZATION) String token, @RequestBody User user) {
    authorizationhelper.authorize(token);
    try {

      userService.save(user);
    }
    catch(Exception ex) {
      throw new TheSameEmailException("E-mail must be unique");
    }
    return user.getId();
  }

  @PutMapping("/users/update/{id}")
  public User putUser(@RequestHeader(AUTHORIZATION) String token,@PathVariable("id") int id, @Valid @RequestBody User userDetails) {
    authorizationhelper.authorize(token);
    User user = userService.getUserById(id);
   

      //provjera ako je rola 1, tj, ako je privilegovani korisnik, da se 
      //omoguci  update korisnika



      if(user.getRole().getRoleId()==1) {
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setCity(userDetails.getCity());
        user.setGender(userDetails.getGender());
        user.setDate_Of_Birth(userDetails.getDate_Of_Birth());
        user.setActive(userDetails.getActive());
        user.setEmail(userDetails.getEmail());
  
        userService.save(user);
      }
      else throw new DontHavePrivilegedException(user.getEmail() + " is not privilaged");
     

     
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

    @PostMapping("/users/register")
    public User registerUser(@Valid @RequestBody UserRegister userRequest) {
    User user = new User();
  
      user.setFirstName(userRequest.getFirstName());
      user.setLastName(userRequest.getLastName());
      user.setCity(userRequest.getCity());
      user.setGender(userRequest.getGender());
      user.setDate_Of_Birth(userRequest.getDateOfBirth());
      user.setActive(userRequest.getActive());
      user.setEmail(userRequest.getEmail());
      user.setRole(userRequest.getRole());

  
      if(userRequest.getPassword().equals(userRequest.getPasswordConfirm())) {
        //kodiraj password u token i sacuvaj u tabelu User

        user.setToken(bCryptPasswordEncoder.encode(userRequest.getPassword()));

        userService.save(user);
      }

      else throw new WrongPasswordException("Password and confirm password can be the same");
    
      return user;
  }
  
   //api za login

   @PostMapping("/users/login")
   public Account loginUser(@Valid @RequestBody UserLogin loginRequest) {
     Account account = new Account();
     
        String userName = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        User user = getUserFromTable(userName);

       //provjera da li taj token postoji u tabeli User
      if(bCryptPasswordEncoder.matches(password, user.getToken())) {
        account.setToken(user.getToken());
        account.setUser(user);
        if(aRepository.findLoggedInUser(user.getId())!=null) {
          return account;
        }
        aRepository.save(account);
        
      }
      else throw new AuthenticationException("");
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


  
  @GetMapping("/users/role")
  public int getRoleByToken(@RequestHeader(AUTHORIZATION) String token) {
    //authorizationhelper.authorize(token);
    List<User> users = null;
    int role = 0;
    try {
      users = userService.getAllUsers();
      for(int i=0; i<users.size(); i++) {
        if(users.get(i).getToken().equals(token)) {
          role = users.get(i).getRole().getRoleId();
        }
      }

    } catch (NoSuchElementException k) {
      throw new UserNotFoundException("User with token: " + token + " not Found");
    }
    return role;
  }


  @GetMapping("/usersAzra")
  public String getAllUsersAzra() {
    amqpTemplate.convertAndSend("userserviceExchange", "userservice", userService.getAllUsers());
    return "Message sent to the RabbitMQ Successfully";
  }
  

}