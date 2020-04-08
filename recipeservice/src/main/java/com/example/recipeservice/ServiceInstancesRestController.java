package com.example.recipeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ServiceInstancesRestController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/serviceinstances/{applicationName}")
	public List<ServiceInstance> serviceInstancesByApplicationName(
			@PathVariable String applicationName) {
		return this.discoveryClient.getInstances(applicationName);
	}

	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public List<String> getRegisteredServices()
	{
		List<String> servisi = new ArrayList<String>();
		discoveryClient.getServices().forEach(servisIme ->
		{
			discoveryClient.getInstances(servisIme).forEach(instance ->
			{
				servisi.add(String.format("%s:%s", servisIme, instance.getUri()));
			});
		});
		return servisi;
	}
}