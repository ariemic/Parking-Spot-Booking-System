package com.example.parkingSystem.config;


import com.example.parkingSystem.dao.BookingRepository;
import com.example.parkingSystem.dao.ParkingRepository;
import com.example.parkingSystem.entity.Booking;
import com.example.parkingSystem.entity.Parking;
import com.example.parkingSystem.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class DataRestParkingController {
    private final ParkingService parkingService;


    @Autowired
    public DataRestParkingController(ParkingRepository parkingRepository, BookingRepository bookingRepository, ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping("/getAllAvailableParkingsNow")
    public ResponseEntity<List<Parking>> getAllAvailableParking() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        return new ResponseEntity<>(parkingService.listAvailableParkings(formattedDate), HttpStatus.OK);
    }




    @GetMapping("/getAllAvailableParkings/{date}")
    public ResponseEntity<List<Parking>> getAllAvailableParking(@PathVariable String date) {
        return new ResponseEntity<>(parkingService.listAvailableParkings(date), HttpStatus.OK);
    }
}
