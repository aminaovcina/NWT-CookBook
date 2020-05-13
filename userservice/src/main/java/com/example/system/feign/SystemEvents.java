package com.example.system.feign;

import com.example.system.models.SystemEvent;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="systemevents")
public interface SystemEvents{

    @PostMapping("/system-events")
    public SystemEvent save(@RequestBody SystemEvent product);

}