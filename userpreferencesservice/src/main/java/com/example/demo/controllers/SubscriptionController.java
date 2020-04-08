package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.errors.exception.SubscriptionNotFoundException;
import com.example.demo.models.FullSubscription;
import com.example.demo.models.Subscription;
import com.example.demo.models.User;
import com.example.demo.models.UserSubscriptions;
import com.example.demo.repositories.SubscriptionRepository;
import com.example.demo.services.SubscriptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(path="/subs")
public class SubscriptionController{
    @Autowired
    public SubscriptionService subscriptionService;
    
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(path = "/add")
    public void addNewSubscription(@RequestParam Integer idSubUser, @RequestParam Integer idUser, @RequestParam Integer type){
        Subscription s = new Subscription(idSubUser, idUser, type);
        subscriptionService.saveOrUpdate(s);
    }

    @RequestMapping(path = "/subUser")
    public @ResponseBody List<FullSubscription> getSubscriptionByIdSubUser(@RequestParam("idSubUser") Integer id){
        List<Subscription> lista = subscriptionService.getSubscriptionBySubUser(id);
        UserSubscriptions userSubscriptions = new UserSubscriptions();
        userSubscriptions.setUserSubscriptions(lista.stream().map(sub -> {
            User user = restTemplate.getForObject("http://eurekauser/user/"+sub.getIdUser(), User.class);
            return new FullSubscription(user, sub.getType());
        })
        .collect(Collectors.toList()));

        return userSubscriptions.getUserSubscriptions();
    } 

    @GetMapping("/all")
    public @ResponseBody List<Subscription> getAllSubscriptions(){
        return subscriptionService.getAllSubscriptions();
    }

    @GetMapping("/{id}")
    public @ResponseBody Subscription getSubscription(@PathVariable("id") Integer id){
        Subscription s=null;
        try 
        {
            s= subscriptionService.getSubscriptionsById(id);
           
        } catch (Exception k)
        {
          throw new SubscriptionNotFoundException("Subscription: "+ id + " not Found" );
        }
        return s;
    }

    @DeleteMapping("/delete/{id}")
    private void deleteSubscription(@RequestBody Integer id) {
        try 
        {
          subscriptionService.delete(id);
        } catch (Exception k)
        {
          throw new SubscriptionNotFoundException("Subscription: "+ id + " not Found" );
        }
    }

}