package com.example.system.controllers;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import com.example.system.dto.UserDto;
import com.example.system.errors.exception.AuthenticationException;
import com.example.system.errors.exception.DontHavePrivilegedException;
import com.example.system.errors.exception.TheSameEmailException;
import com.example.system.errors.exception.UserNotFoundException;
import com.example.system.errors.exception.WrongPasswordException;
import com.example.system.feign.RecipeUser;
import com.example.system.helper.ApplicationConfigReader;
import com.example.system.helper.ApplicationConstants;
import com.example.system.helper.AuthorizationHelper;
import com.example.system.models.Account;
import com.example.system.models.Recipe;
import com.example.system.models.Role;
import com.example.system.models.User;
import com.example.system.models.UserEdit;
import com.example.system.models.UserLogin;
import com.example.system.models.UserRegister;
import com.example.system.rabbit.RabbitSender;
import com.example.system.repositories.AccountRepository;
import com.example.system.repositories.RoleRepository;
import com.example.system.repositories.UserRepository;
import com.example.system.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
public class UserController {
  public static final String AUTHORIZATION= "Authorization";

  private static final Logger log = LoggerFactory.getLogger(UserService.class);
  private final RabbitTemplate rabbitTemplate;
  private ApplicationConfigReader applicationConfig;
  private RabbitSender messageSender;
  public ApplicationConfigReader getApplicationConfig() {
      return applicationConfig;
  }
  @Autowired
  public void setApplicationConfig(ApplicationConfigReader applicationConfig) {
      this.applicationConfig = applicationConfig;
  }

  public RabbitSender getMessageSender() {
		return messageSender;
	}

	@Autowired
	public void setMessageSender(RabbitSender messageSender) {
		this.messageSender = messageSender;
	}

  @Autowired
  UserService userService;

  @Autowired
  RecipeUser recipeUser;

  @Autowired
  AccountRepository aRepository;

  @Autowired
  AuthorizationHelper authorizationhelper;

  @Autowired
  RoleRepository rRepository;


  @Autowired
    private AmqpTemplate amqpTemplate;   
  


  private UserRepository applicationUserRepository;
  private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

  @Autowired
  public UserController(UserRepository applicationUserRepository,
                        BCryptPasswordEncoder bCryptPasswordEncoder,
                        final RabbitTemplate rabbitTemplate) {
      this.applicationUserRepository = applicationUserRepository;
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
      this.rabbitTemplate = rabbitTemplate;
  }

  @PostMapping("rabbit/test")
  public ResponseEntity<?> sendMessage(@RequestBody User user) {
    String exchange = getApplicationConfig().getApp2Exchange();
    String routingKey = getApplicationConfig().getApp2RoutingKey();
    /* Sending to Message Queue */
    try {
      //Write user
      messageSender.sendMessage(rabbitTemplate, exchange, routingKey, user.toString());
      return new ResponseEntity<String>(ApplicationConstants.IN_QUEUE, HttpStatus.OK);
    } catch (Exception ex) {
      log.error("Exception occurred while sending message to the queue. Exception= {}", ex);
      return new ResponseEntity(ApplicationConstants.MESSAGE_QUEUE_SEND_ERROR,
      HttpStatus.INTERNAL_SERVER_ERROR);
  }
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
  public User putUser(@RequestHeader(AUTHORIZATION) String token,@PathVariable("id") int id, @Valid @RequestBody UserEdit userDetails) {
    authorizationhelper.authorize(token);
    User user = userService.getUserById(id);
   
    if( userDetails.getCurrentPassword()!=null &&
    userDetails.getPassword() != null && userDetails.getPasswordConfirm()!=null) {
      String currentPassword = userDetails.getCurrentPassword();
      String password = userDetails.getPassword();
      String confirmPassword = userDetails.getPasswordConfirm();
      
      if(!bCryptPasswordEncoder.matches(currentPassword, user.getToken()) || 
         !password.equals(confirmPassword)) {
            throw new AuthenticationException("wrong password!");
         }
      user.setToken(bCryptPasswordEncoder.encode(password));
    }

   
      user.setFirstName(userDetails.getFirstName()!=null?userDetails.getFirstName():user.getFirstName());
      user.setLastName(userDetails.getLastName()!=null?userDetails.getLastName():user.getLastName());
      user.setCity(userDetails.getCity()!=null?userDetails.getCity():user.getCity());
      user.setGender(userDetails.getGender()!=null?userDetails.getGender():user.getGender());
      user.setDate_Of_Birth(userDetails.getDateOfBirth()!=null?userDetails.getDateOfBirth():user.getDate_Of_Birth());
      user.setActive(userDetails.getActive()!=null?userDetails.getActive():user.getActive());
      user.setEmail(userDetails.getEmail()!=null?userDetails.getEmail():user.getEmail());
      user.setRole(userDetails.getRole()!=null?userDetails.getRole():user.getRole());

      
      userService.save(user);
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

  
      if(userRequest.getPassword().equals(userRequest.getPasswordConfirm()) && !checkEmailInTable(userRequest.getEmail())) {
        //kodiraj password u token i sacuvaj u tabelu User

        user.setToken(bCryptPasswordEncoder.encode(userRequest.getPassword()));

        userService.save(user);
      }

      else throw new WrongPasswordException("Passwords don't match or email already exists!");
    
      return user;
  }

  public boolean checkEmailInTable(String email) {

    List<User> users = userService.getAllUsers();
    for(int i=0; i<users.size(); i++) {
      if(users.get(i).getEmail().equals(email))
       return true;
    }
    return false;
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
  public Role getRoleByToken(@RequestHeader(AUTHORIZATION) String token) {
    //authorizationhelper.authorize(token);
    List<User> users = null;
    Role role = null;
    try {
      users = userService.getAllUsers();
      for(int i=0; i<users.size(); i++) {
        if(("Bearer " + users.get(i).getToken()).equals(token)) {
          role = users.get(i).getRole();
          break;
        }
      }

    } catch (NoSuchElementException k) {
      throw new UserNotFoundException("User with token: " + token + " not Found");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return role;
  }



}