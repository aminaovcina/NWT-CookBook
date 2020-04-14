package com.example.demo.controllers;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.errors.exception.ListIsEmptyException;
import com.example.demo.errors.exception.SubscriptionAlreadyExistsException;
import com.example.demo.errors.exception.SubscriptionNotFoundException;
import com.example.demo.models.FullSubscription;
import com.example.demo.models.Subscription;
import com.example.demo.models.User;
import com.example.demo.models.UserSubscriptions;
import com.example.demo.repositories.SubscriptionRepository;
import com.example.demo.services.SubscriptionService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
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
    
    @Autowired
    EurekaClient eurekaClient;

    public SubscriptionController(EurekaClient eurekaClient){
        this.eurekaClient=eurekaClient;
    }

    //pokusaj pronalaska problema
    //iz nekog razloga getApplications() pronalazi, ali getApplication ne da instance, nisam uspjela rijesiti problem
    //KAd podignem eureku adrese servisa su ip adrese, a ne localhost
    @GetMapping(path = "/proba")
    public void getApplicationEureka() {

        //String url="";
        eurekaClient.getApplications();
        Iterator i = eurekaClient.getApplications().getRegisteredApplications().iterator();

        while (i.hasNext()) {
            Application app = (Application) i.next();
            System.out.println("_____________x__________");
            System.out.println(app.getName());
           // if(app.getName().equals("USERSERVICE"))
             //   url=app.getInstancesAsIsFromEureka().get(0).getIPAddr();
        }
       // System.out.println("čččč"+url);
        //ServiceInstance serviceInstance=loadBalancer.choose("userservice");
        //System.out.println(serviceInstance.getUri());
        InstanceInfo app = (InstanceInfo) eurekaClient.getApplication("userservice").getInstances().get(0);
        System.out.println("čččč"+app.getHomePageUrl());
    }

    @PostMapping(path = "/add")
    public Subscription saveSubscription(@RequestBody Subscription sub){
        List<Subscription> lista = subscriptionService.getSubscriptionsByUser(sub.getIdUser());
        for (Subscription s : lista) {
            if(s.getIdSubUser().equals(sub.getIdSubUser()))
                throw new SubscriptionAlreadyExistsException(s.getIdUser(), s.getIdSubUser());
        }
        subscriptionService.save(sub);
        return sub;
    }

    @GetMapping(path = "/subUser/{id}")
    public @ResponseBody List<User> getSubscriptionByIdUser(@PathVariable("id") Integer id){
        List<User> korisnici;
        try{
        List<Subscription> lista = subscriptionService.getSubscriptionsByUser(id);
        Application application = eurekaClient.getApplication("userservice");
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String hostname = instanceInfo.getIPAddr();
        int port = instanceInfo.getPort();
        korisnici=(lista.stream().map(sub -> {
            User user = restTemplate.getForObject("http://"+hostname+":"+port+"/user/"+sub.getIdUser(), User.class);
            return user;
        })
        .collect(Collectors.toList()));
    } catch(Exception k){
        throw new SubscriptionNotFoundException("User s ID-em "+id + "nije se subscribe-ao ni na jednog korisnika");
    }
        return korisnici;
    } 

    @GetMapping(path = "/user/{idUser}")
    public @ResponseBody List<Subscription> getSubscriptionsByUser(@PathVariable("idUser") Integer id) {
        List<Subscription> lista;
        try{
            lista=subscriptionService.getSubscriptionsByUser(id);
            if(lista.size()==0) throw new SubscriptionNotFoundException("User s ID-em " + id + "nije se subscribe-ao niti na jednog korisnika");
        } catch(Exception k){
        throw new SubscriptionNotFoundException("User s ID-em " + id + "nije se subscribe-ao niti na jednog korisnika");
    }
        return lista;
    }

    @GetMapping("/all")
    public @ResponseBody List<Subscription> getAllSubscriptions(){
        List<Subscription> lista;
        try{
             lista = subscriptionService.getAllSubscriptions();
            if(lista.size()==0) throw new SubscriptionNotFoundException("List is empty");
        }catch (Exception k)
        {
          throw new SubscriptionNotFoundException("List is Empty");
        }
        return lista;
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
    private void deleteSubscription(@PathVariable("id") Integer id) {
        try 
        {
          subscriptionService.delete(id);
        } catch (Exception k)
        {
          throw new SubscriptionNotFoundException("Subscription: "+ id + " not Found" );
        }
    }

}