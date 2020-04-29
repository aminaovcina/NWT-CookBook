package com.example.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemEventService {

    @Autowired
    SystemEventRepository seRepository;

    
    public void save(SystemEvent user) {
        seRepository.save(user);
    }


}