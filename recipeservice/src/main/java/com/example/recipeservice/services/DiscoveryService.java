package com.example.recipeservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscoveryService {
    @Autowired
    private DiscoveryClient discoveryClient;

    public String getUserInstance() {
    List<String> services = new ArrayList<String>();
        discoveryClient.getInstances("userservice").forEach(instance ->
        {
            services.add(String.format("%s", instance.getUri()));
        });
		return services.get(0);
    }
}