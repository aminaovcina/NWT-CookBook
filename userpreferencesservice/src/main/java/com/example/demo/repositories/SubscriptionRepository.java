package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Subscription;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    public List<Subscription> findByIdSubUser(Integer idSubUser);

    public List<Subscription> findByIdUser(Integer idUser);
}