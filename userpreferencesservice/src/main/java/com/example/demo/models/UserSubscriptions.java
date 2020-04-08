package com.example.demo.models;

import java.util.List;

public class UserSubscriptions {
    
    private List <FullSubscription> userSubscriptions;
    
    public List<FullSubscription> getUserSubscriptions(){
        return userSubscriptions;
    }
    public void setUserSubscriptions(List<FullSubscription> subs){
        this.userSubscriptions = subs;
    }
}