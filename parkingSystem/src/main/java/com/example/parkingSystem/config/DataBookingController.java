package com.example.parkingSystem.config;

import com.example.parkingSystem.dao.BookingRepository;
import com.example.parkingSystem.dao.ParkingRepository;
import com.example.parkingSystem.entity.Booking;
import com.example.parkingSystem.entity.Parking;
import com.example.parkingSystem.services.ParkingService;
import com.example.parkingSystem.services.SubscriberService;
import com.example.parkingSystem.validation.DateValidation;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//@Validated
@Controller
public class DataBookingController {
    private final BookingRepository bookingRepository;
    private final ParkingService parkingService;
    private final SubscriberService subscriberService;
    private final ParkingRepository parkingRepository;

    @Autowired
    public DataBookingController(BookingRepository bookingRepository, ParkingService parkingService,
                                 SubscriberService subscriberService, ParkingRepository parkingRepository) {
        this.bookingRepository = bookingRepository;
        this.parkingService = parkingService;
        this.subscriberService = subscriberService;
        this.parkingRepository = parkingRepository;
    }


    @PostMapping("/bookings")
    public ResponseEntity<String> saveBooking(@Validated @RequestBody Booking bookingsToSave){
            String carRegistration = bookingsToSave.getSubscriberCarRegistration();
            int parkingId = bookingsToSave.getParkingId();
            String bookingDate = bookingsToSave.getBookingDate();


            if(!subscriberService.subscriberExist(carRegistration)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nie znalezionego podanego subskrybenta");
            }
            if(!parkingService.parkingExist(parkingId)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nie znaleziono podanego parkingu");
            }

            Parking parking = parkingRepository.findByParkingId(parkingId);

            if(!subscriberService.hasLicense(carRegistration, parkingId)){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Subskrybent o numerze rejestracyjnym" +  carRegistration +
                        "nie posiada licencji na parking: " + parkingId);
            }


            if(!parkingService.listAvailableParkings(bookingDate).contains(parking)){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("W dniu " + bookingDate +
                        " nie mamy już miejsca na parking " + parkingId);
            }

            bookingRepository.save(bookingsToSave);
            return ResponseEntity.ok("Utworzono rezerwację na dzień: " + bookingDate);

    }


    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> bookingList(){
        List<Booking> bookingList = bookingRepository.findAll();
        return ResponseEntity.ok(bookingList);
    }




}
