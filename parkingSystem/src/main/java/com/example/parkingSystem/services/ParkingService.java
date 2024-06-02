package com.example.parkingSystem.services;

import com.example.parkingSystem.dao.BookingRepository;
import com.example.parkingSystem.dao.ParkingRepository;
import com.example.parkingSystem.dao.SubscriberRepository;
import com.example.parkingSystem.dto.ParkingDetails;
import com.example.parkingSystem.dto.ParkingSubscribers;
import com.example.parkingSystem.dto.SubscriberDto;
import com.example.parkingSystem.entity.Booking;
import com.example.parkingSystem.entity.Parking;
import com.example.parkingSystem.entity.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ParkingService {

    private ParkingRepository parkingRepository;
    private BookingRepository bookingRepository;
    private final SubscriberRepository subscriberRepository;
    private final SubscriberService subscriberService;


    @Autowired
    public ParkingService(ParkingRepository parkingRepository, BookingRepository bookingRepository,
                          SubscriberRepository subscriberRepository, SubscriberService subscriberService) {
        this.parkingRepository = parkingRepository;
        this.bookingRepository = bookingRepository;
        this.subscriberRepository = subscriberRepository;
        this.subscriberService = subscriberService;
    }


    public boolean parkingExist(int parkingId){
       return parkingRepository.existsById(parkingId);
    }


    private Map<Parking, Integer> calculateBookedSlots(String date){
        List<Parking> parkings = parkingRepository.findAll();
        List<Booking> bookings = bookingRepository.findAll();
        //przechowuje liczbe <Parking, liczba zajetych miejsc>
        Map<Parking, Integer> parkingMap = new HashMap<>();
        for (Parking parking: parkings){
            parkingMap.put(parking, 0);
        }
        for(Booking booking: bookings){
            if(booking.getBookingDate().equals(date)){
                int parkingId = booking.getParkingId();
                Parking bookedParking = parkingRepository.findByParkingId(parkingId);
                parkingMap.put(bookedParking, parkingMap.get(bookedParking) + 1);
            }
        }
        return parkingMap;

    }

    public List<ParkingDetails> listAvailableParkings(String date){
        Map<Parking, Integer> parkingMap = calculateBookedSlots(date);
        List<ParkingDetails> availableParkingsNow = new ArrayList<>();

        for(Map.Entry<Parking, Integer> entry: parkingMap.entrySet()){
            Parking potentialParking = entry.getKey();
            int bookedSlots = entry.getValue();
            int freeSlots = potentialParking.getMaxSlots() - bookedSlots;
            if(freeSlots > 0){
                ParkingDetails parkingDetails = new ParkingDetails(potentialParking, freeSlots);
                availableParkingsNow.add(parkingDetails);
            }}

        return availableParkingsNow;
    }


    public List<ParkingSubscribers> listAllParkingSubscribers(String date) {

        List<Subscriber> subscribers = subscriberService.activeLicenseSubscribers(date);

        List<Parking> parkings = parkingRepository.findAll();
        List<ParkingSubscribers> parkingSubscribersList = new ArrayList<>();

        for (Parking parking : parkings) {
            int parkingId = parking.getParkingId();

            ParkingSubscribers parkingSubscribers = new ParkingSubscribers();
            parkingSubscribers.setParkingId(parkingId);
            parkingSubscribers.setCoordinates(parking.getCoordinates());
            parkingSubscribers.setAddress(parking.getAddress());


            List<SubscriberDto> subscriberDtoList = getSubscriberDtoList(subscribers, parkingId);
            parkingSubscribers.setSubscribers(subscriberDtoList);

            parkingSubscribersList.add(parkingSubscribers);
        }
        return parkingSubscribersList;
    }

    private static List<SubscriberDto> getSubscriberDtoList(List<Subscriber> subscribers, int parkingId) {
        List<SubscriberDto> subscriberDtoList = new ArrayList<>();
        for (Subscriber subscriber : subscribers) {
            boolean hasAllParkingSubscription = subscriber.isAllParkings();
            int subscriberMainParking = subscriber.getMainParking();

            if(hasAllParkingSubscription || subscriberMainParking == parkingId){
                SubscriberDto subscriberDto = new SubscriberDto(subscriber.getCarRegistration(), subscriber.getFirstName(), subscriber.getLastName());
                subscriberDtoList.add(subscriberDto);
            }
        }
        return subscriberDtoList;
    }

}


