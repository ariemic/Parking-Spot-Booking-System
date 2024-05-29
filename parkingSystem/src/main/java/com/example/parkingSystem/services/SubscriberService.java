package com.example.parkingSystem.services;

import com.example.parkingSystem.dao.SubscriberRepository;
import com.example.parkingSystem.entity.Subscriber;
import com.example.parkingSystem.exceptions.SubscriberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Optional;


@Service
public class SubscriberService {

    private SubscriberRepository subscriberRepository;

    @Autowired
    public SubscriberService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }


    public boolean subscriberExist(String subscriberCarRegistration){
        return subscriberRepository.existsById(subscriberCarRegistration);
    }

    public boolean hasLicense(String subscriberCarRegistration, int parkingId){
        Optional<Subscriber> OptionalSubscriber = subscriberRepository.findById(subscriberCarRegistration);


        if(!OptionalSubscriber.isPresent()){
//            throw new SubscriberNotFoundException("Subskrybent o podanym numerze rejestracyjnm nie istnieje");
            return false;

        }

        Subscriber subscriber = OptionalSubscriber.get();
        if(subscriber.isAllParkings()){
            return true;
        }

        if(subscriber.getMainParking() == null){
            return false;
        }

        return subscriber.getMainParking() == parkingId;

    }


}
