package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Subscription;
import com.example.demo.repositories.SubscriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Subscription getSubscriptionsById(int id) {
        return subscriptionRepository.findById(id).get();
    }

    public List<Subscription> getSubscriptionBySubUser(int id) {
        return subscriptionRepository.findByIdSubUser(id);
    }
    
    public List<Subscription> getSubscriptioneByUser(int id) {
        return subscriptionRepository.findByIdUser(id);
    }

    public void saveOrUpdate(Subscription sub) {
        subscriptionRepository.save(sub);
    }

    public void delete(int id) {
        subscriptionRepository.deleteById(id);
    }

    public void findOne(int id) {
        subscriptionRepository.deleteById(id);
    }

    public Subscription createSubscription(Integer idSubUser, Integer idUser){
        Subscription sub = new Subscription(idSubUser, idUser);
        subscriptionRepository.save(sub);
        return sub;
    }
}