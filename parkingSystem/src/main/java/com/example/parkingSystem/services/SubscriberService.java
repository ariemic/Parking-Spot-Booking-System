package com.example.parkingSystem.services;

import com.example.parkingSystem.dao.BookingRepository;
import com.example.parkingSystem.dao.SubscriberRepository;
import com.example.parkingSystem.entity.Booking;
import com.example.parkingSystem.entity.Subscriber;
import com.example.parkingSystem.exceptions.SubscriberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class SubscriberService {


    private final SubscriberRepository subscriberRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public SubscriberService(SubscriberRepository subscriberRepository, BookingRepository bookingRepository) {
        this.subscriberRepository = subscriberRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Subscriber> activeLicenseSubscribers(String date){
        List<Subscriber> allSubscribers = subscriberRepository.findAll();
        List<Subscriber> activeLicenseSubscribers = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.parse(date, formatter);
        for(Subscriber subscriber: allSubscribers){
            String endDate =  subscriber.getEndDate().toString();
            endDate = endDate.substring(0,10);
            LocalDate licenseEnd = LocalDate.parse(endDate, formatter);
            if(licenseEnd.isAfter(currentDate) || licenseEnd.isEqual(currentDate)){
                activeLicenseSubscribers.add(subscriber);
            }
        }
        return activeLicenseSubscribers;

    }


    public boolean subscriberExist(String subscriberCarRegistration){
        return subscriberRepository.existsById(subscriberCarRegistration);
    }

    public boolean isSubscriptionActive(Subscriber subscriber, String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.parse(date, formatter);
        String endDate =  subscriber.getEndDate().toString();
        endDate = endDate.substring(0,10);
        LocalDate licenseEnd = LocalDate.parse(endDate, formatter);
        if(licenseEnd.isAfter(currentDate) || licenseEnd.isEqual(currentDate)){
           return true;
        }
        return false;
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


    public String deleteSubscriber(String subscriberCarRegistration){
        if(!subscriberExist(subscriberCarRegistration)){
           throw new SubscriberNotFoundException();

        }
        Optional<Subscriber> subscriberToDelete = subscriberRepository.findById(subscriberCarRegistration);
        Subscriber subscriber = subscriberToDelete.get();
        List<Booking> bookingList = bookingRepository.findBookingBySubscriberCarRegistration(subscriberCarRegistration);

        bookingRepository.deleteAll(bookingList);
        subscriberRepository.delete(subscriber);

        return "Usunięto subskrybenta o rejestracji: " + subscriberCarRegistration + " oraz " + bookingList.toArray().length + " jego rezerwacji";

    }


    public Subscriber getSubscriber(String subscriberCarRegistration){
        if(!subscriberExist(subscriberCarRegistration)){
            throw new SubscriberNotFoundException();
        }
        Optional<Subscriber> subscriber = subscriberRepository.findById(subscriberCarRegistration);
        return subscriber.orElseThrow(SubscriberNotFoundException::new);
    }


    public Subscriber putSubscriber(String subscriberCarRegistration, Subscriber newSubscriberData){
        if(!subscriberExist(subscriberCarRegistration)){
            throw new SubscriberNotFoundException();
        }

        return subscriberRepository.findById(subscriberCarRegistration)
                .map(subscriber -> {
                    subscriber.setFirstName(newSubscriberData.getFirstName());
                    subscriber.setLastName(newSubscriberData.getLastName());
                    subscriber.setMainParking(newSubscriberData.getMainParking());
                    subscriber.setEndDate(newSubscriberData.getEndDate());
                    subscriber.setAllParkings(newSubscriberData.isAllParkings());
                    return subscriberRepository.save(subscriber);
                }).orElseThrow(SubscriberNotFoundException::new);
    }


    public Subscriber patchSubscriber(String carRegistration, Subscriber newSubscriberData) {
        return subscriberRepository.findById(carRegistration)
                .map(subscriber -> {
                    if (newSubscriberData.getFirstName() != null) {
                        subscriber.setFirstName(newSubscriberData.getFirstName());
                    }
                    if (newSubscriberData.getLastName() != null) {
                        subscriber.setLastName(newSubscriberData.getLastName());
                    }
                    if (newSubscriberData.getEndDate() != null) {
                        subscriber.setEndDate(newSubscriberData.getEndDate());
                    }
                    if (newSubscriberData.getMainParking() != null) {
                        subscriber.setMainParking(newSubscriberData.getMainParking());
                    }
                    subscriber.setAllParkings(newSubscriberData.isAllParkings());
                    return subscriberRepository.save(subscriber);
                })
                .orElseThrow(SubscriberNotFoundException::new);
    }


}
