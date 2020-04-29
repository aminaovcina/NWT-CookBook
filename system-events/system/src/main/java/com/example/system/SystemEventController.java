package com.example.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemEventController {

    @Autowired
    SystemEventService seService;

    @PostMapping("/system-events")
    private int saveUser(@RequestBody SystemEvent user) {
    
        seService.save(user);

      return user.getId();
    }
  

}