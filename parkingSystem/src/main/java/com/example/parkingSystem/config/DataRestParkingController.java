package com.example.parkingSystem.config;


import com.example.parkingSystem.dao.BookingRepository;
import com.example.parkingSystem.dao.ParkingRepository;
import com.example.parkingSystem.entity.Booking;
import com.example.parkingSystem.entity.Parking;
import com.example.parkingSystem.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class DataRestParkingController {

    private final ParkingRepository parkingRepository;
    private final BookingRepository bookingRepository;
    private final ParkingService parkingService;


    @Autowired
    public DataRestParkingController(ParkingRepository parkingRepository, BookingRepository bookingRepository) {
        this.parkingRepository = parkingRepository;
        this.bookingRepository = bookingRepository;
        this.parkingService = new ParkingService(parkingRepository);
    }

    @GetMapping("/getAllAvailableParkingsNow")
    public ResponseEntity<List<Parking>> getAllAvailableParking() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        List<Parking> parkings = parkingRepository.findAll();
        List<Booking> bookings = bookingRepository.findAll();


        Map<Parking, Integer> parkingMap = new HashMap<>();

        for (Parking parking : parkings) {
            parkingMap.put(parking, 0);
        }
        for (Booking booking : bookings) {
            if(booking.getBookingDate().equals(formattedDate)){
                Parking bookedParking = booking.getParking();
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
        return new ResponseEntity<>(availableParkingsNow, HttpStatus.OK);
    }




    @GetMapping("/getAllAvailableParkings/{date}")
    public ResponseEntity<List<Parking>> getAllAvailableParking(@PathVariable String date) {


        List<Parking> parkings = parkingRepository.findAll();
        List<Booking> bookings = bookingRepository.findAll();


        Map<Parking, Integer> parkingMap = new HashMap<>();

        for (Parking parking : parkings) {
            parkingMap.put(parking, 0);
        }
        for (Booking booking : bookings) {

            if(booking.getBookingDate().equals(date)){
                Parking bookedParking = booking.getParking();
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
        return new ResponseEntity<>(availableParkingsNow, HttpStatus.OK);
    }
}
