package com.example.parkingSystem.config;


import com.example.parkingSystem.dao.SubscriberRepository;
import org.springframework.stereotype.Controller;

@Controller
public class DataSubscriberController {
    private final SubscriberRepository subscriberRepository;


    public DataSubscriberController(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }


}
