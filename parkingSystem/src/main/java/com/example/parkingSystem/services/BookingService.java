package com.example.parkingSystem.services;


import com.example.parkingSystem.dao.BookingRepository;
import com.example.parkingSystem.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ParkingService parkingService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, ParkingService parkingService) {
        this.bookingRepository = bookingRepository;
        this.parkingService = parkingService;
    }

    public ResponseEntity<Booking> findBookingById(int bookingId){
        try {
           Booking foundBooking = bookingRepository.findByBookingId(bookingId);
            if (foundBooking != null) {
                return ResponseEntity.ok(foundBooking);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

