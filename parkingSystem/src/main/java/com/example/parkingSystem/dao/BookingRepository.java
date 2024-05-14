package com.example.parkingSystem.dao;

import com.example.parkingSystem.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findByBookingId(int bookingId);

    List<Booking> findByParkingId(int parkingId);

    List<Booking> findBySubscriberCarRegistration(String subscriberCarRegistration);

    List<Booking> findByBookingDate(String bookingDate);
}

