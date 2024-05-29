package com.example.parkingSystem.config;

import com.example.parkingSystem.dao.BookingRepository;
import com.example.parkingSystem.dao.ParkingRepository;
import com.example.parkingSystem.entity.Booking;
import com.example.parkingSystem.entity.Parking;
import com.example.parkingSystem.exceptions.ParkingNotFoundException;
import com.example.parkingSystem.exceptions.SubscriberNotFoundException;
import com.example.parkingSystem.services.BookingService;
import com.example.parkingSystem.services.ParkingService;
import com.example.parkingSystem.services.SubscriberService;
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

    private final BookingService bookingService;

    @Autowired
    public DataBookingController(BookingRepository bookingRepository, ParkingService parkingService,
                                 SubscriberService subscriberService, ParkingRepository parkingRepository, BookingService bookingService) {
        this.bookingRepository = bookingRepository;
        this.parkingService = parkingService;
        this.subscriberService = subscriberService;
        this.parkingRepository = parkingRepository;
        this.bookingService = bookingService;
    }


//    @PostMapping("/bookings")
//    public ResponseEntity<String> saveBooking(@Validated @RequestBody List<Booking> bookingsToSave){
//        try {
//            for(Booking booking: bookingsToSave){
//                if(!parkingService.listAvailableParkings(booking.getBookingDate()).contains(booking.getParking())){
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Na parkingu " + booking.getParking().getAddress() + "nie ma już miejsca w ten dzień");
//                }
//            }
//            bookingRepository.saveAll(bookingsToSave);
//            return ResponseEntity.ok("Pomyślnie zapisano ");
//        } catch (Exception ex){
//
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wystąpił błąd walidacji danych" + ex);
//        }
//
//
//    }

    @PostMapping("/bookings")
    public ResponseEntity<String> saveBooking(@Validated @RequestBody Booking bookingToSave) {
        try {
            bookingService.saveBooking(bookingToSave);
            return ResponseEntity.ok("Utworzono rezerwację na dzień: " + bookingToSave.getBookingDate());
        } catch (SubscriberNotFoundException | ParkingNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Wystąpił błąd podczas tworzenia rezerwacji");
        }

    }




    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> bookingList(){
        List<Booking> bookingList = bookingRepository.findAll();
        return ResponseEntity.ok(bookingList);
    }




}
