package com.example.parkingSystem.services;

import com.example.parkingSystem.dao.BookingRepository;
import com.example.parkingSystem.dao.ParkingRepository;
import com.example.parkingSystem.entity.Booking;
import com.example.parkingSystem.entity.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParkingService {

    private ParkingRepository parkingRepository;
    private BookingRepository bookingRepository;



    @Autowired
    public ParkingService(ParkingRepository parkingRepository, BookingRepository bookingRepository) {
        this.parkingRepository = parkingRepository;
        this.bookingRepository = bookingRepository;
    }





    public List<Parking> listAvailableParkings(String date){


        List<Parking> parkings = parkingRepository.findAll();
        List<Booking> bookings = bookingRepository.findAll();


        Map<Parking, Integer> parkingMap = new HashMap<>();

        for (Parking parking : parkings) {
            parkingMap.put(parking, 0);
        }
        for (Booking booking : bookings) {

            if(booking.getBookingDate().equals(date)){
                int parkingId = booking.getParkingId();
                Parking bookedParking = parkingRepository.findByParkingId(parkingId);
                parkingMap.put(bookedParking, parkingMap.get(bookedParking) + 1);
            }

        }
        List<Parking> availableParkingsNow = new ArrayList<>();

        for(Map.Entry<Parking, Integer> entry: parkingMap.entrySet()){
            Parking potentialParking = entry.getKey();
            int bookedSlots = entry.getValue();
            if(bookedSlots < potentialParking.getMaxSlots()){
                availableParkingsNow.add(potentialParking);
            }
        }
        return availableParkingsNow;

    }
}
