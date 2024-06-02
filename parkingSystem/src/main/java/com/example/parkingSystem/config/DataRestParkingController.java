package com.example.parkingSystem.config;


import com.example.parkingSystem.dao.BookingRepository;
import com.example.parkingSystem.dao.ParkingRepository;

import com.example.parkingSystem.dto.ParkingSummary;
import com.example.parkingSystem.entity.Parking;
import com.example.parkingSystem.exceptions.WrongDateFormatException;

import com.example.parkingSystem.dto.ParkingDetails;
import com.example.parkingSystem.dto.ParkingSubscribers;

import com.example.parkingSystem.services.ParkingService;
import com.example.parkingSystem.services.ParkingSummaryService;
import com.example.parkingSystem.validation.DateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class DataRestParkingController {
    private final ParkingService parkingService;
    private final ParkingRepository parkingRepository;
    private final ParkingSummaryService parkingSummaryService;


    @Autowired
    public DataRestParkingController(ParkingRepository parkingRepository, BookingRepository bookingRepository, ParkingService parkingService, ParkingSummaryService parkingSummaryService) {
        this.parkingService = parkingService;
        this.parkingRepository = parkingRepository;
        this.parkingSummaryService = parkingSummaryService;
    }

    @GetMapping("/getAllAvailableParkingsNow")
    public ResponseEntity<List<ParkingDetails>> getAllAvailableParking() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        return new ResponseEntity<>(parkingService.listAvailableParkings(formattedDate), HttpStatus.OK);
    }


    @GetMapping("/getAllAvailableParkings/{date}")
    public ResponseEntity<?> getAllAvailableParking(@PathVariable String date) {

        DateValidation dateValidation = DateValidation.getInstance();
        if (!dateValidation.validateDate(date)) {
            return new ResponseEntity<>("Niepoprawny format daty", HttpStatus.BAD_REQUEST);
        }

        List<ParkingDetails> availableParkings = parkingService.listAvailableParkings(date);
        return new ResponseEntity<>(availableParkings, HttpStatus.OK);
    }


    @GetMapping("/getReport/{date1}/{date2}")
    public ResponseEntity<Object> getParkingSummary(@PathVariable String date1, @PathVariable String date2) {
        try {
            List<ParkingSummary> parkingSummaries = parkingSummaryService.parkingSummaries(date1, date2);
            return ResponseEntity.ok(parkingSummaries);
        } catch (WrongDateFormatException e) {
            return ResponseEntity.badRequest().body("Niepoprawny format daty. Wymagany format: yyyy-MM-dd");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Wystąpił nieoczekiwany błąd podczas" +
                    " pobierania podsumowania parkingu: " + e.getMessage() );
        }
    }

//    @GetMapping("/parkings")
//    public List<Parking> getAllParkings() {
//        List<Parking> parkings = parkingRepository.findAll();
//        parkings.forEach(Parking::initFreeSlots);
//        return parkings;
//    }
//
//    @GetMapping("/parkings/{parkingId}")
//    public Parking getParking(@PathVariable Integer parkingId) {
//        Parking parking = parkingRepository.findByParkingId(parkingId);
//        parking.initFreeSlots();
//        return parking;
//    }

    @GetMapping("/listAllParkingSubscribers/{date}")
    public ResponseEntity<?> listAllParkingSubscribers(@PathVariable String date){

        DateValidation dateValidation = DateValidation.getInstance();
        if (!dateValidation.validateDate(date)) {
            return new ResponseEntity<>("Niepoprawny format daty", HttpStatus.BAD_REQUEST);
        }

        List<ParkingSubscribers> parkingSubscribersList = parkingService.listAllParkingSubscribers(date);
        return new ResponseEntity<>(parkingSubscribersList, HttpStatus.OK);

    }





}
