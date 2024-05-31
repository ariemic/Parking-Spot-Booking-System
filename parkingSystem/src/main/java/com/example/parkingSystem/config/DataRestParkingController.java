package com.example.parkingSystem.config;


import com.example.parkingSystem.dao.BookingRepository;
import com.example.parkingSystem.dao.ParkingRepository;
import com.example.parkingSystem.entity.Parking;
import com.example.parkingSystem.exceptions.ParkingNotFoundException;
import com.example.parkingSystem.services.ParkingService;
import com.example.parkingSystem.validation.DateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class DataRestParkingController {
    private final ParkingService parkingService;
    private final ParkingRepository parkingRepository;


    @Autowired
    public DataRestParkingController(ParkingRepository parkingRepository, BookingRepository bookingRepository, ParkingService parkingService) {
        this.parkingService = parkingService;
        this.parkingRepository = parkingRepository;
    }

    @GetMapping("/getAllAvailableParkingsNow")
    public ResponseEntity<List<Parking>> getAllAvailableParking() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        return new ResponseEntity<>(parkingService.listAvailableParkings(formattedDate), HttpStatus.OK);
    }


    @GetMapping("/getAllAvailableParkings/{date}")
    public ResponseEntity<?> getAllAvailableParking(@PathVariable String date) {
        // ------------------------------------------------------------------
        DateValidation dateValidation = DateValidation.getInstance();
        if (!dateValidation.validateDate(date)) {
            return new ResponseEntity<>("Niepoprawny format daty", HttpStatus.BAD_REQUEST);
        }
        // ------------------------------------------------------------------

        List<Parking> availableParkings = parkingService.listAvailableParkings(date);
        return new ResponseEntity<>(availableParkings, HttpStatus.OK);
    }

    //todo dodanie przykladow do bazy i sprawdzenie czy logika działą
    @GetMapping("/getNumberOfFreeSlotsToday/{parkingId}")
    public ResponseEntity<?> getNumberOfFreeSlots(@PathVariable Integer parkingId){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);
        try {
            if (!parkingService.parkingExist(parkingId)) {
                throw new ParkingNotFoundException();
            }
            return new ResponseEntity<>(parkingService.numberOfFreeSlots(parkingId, formattedDate), HttpStatus.OK);
        } catch (ParkingNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Wystąpił nieoczekiwany błąd");
        }

    }
    @GetMapping("/getNumberOfFreeSlots/{parkingId}/{date}")
    public ResponseEntity<?> getNumberOfFreeSlots(@PathVariable Integer parkingId, @PathVariable String date){
        // ------------------------------------------------------------------
        DateValidation dateValidation = DateValidation.getInstance();
        if (!dateValidation.validateDate(date)) {
            return new ResponseEntity<>("Niepoprawny format daty", HttpStatus.BAD_REQUEST);
        }
        // ------------------------------------------------------------------
        return new ResponseEntity<>(parkingService.numberOfFreeSlots(parkingId, date), HttpStatus.OK);
    }



}
