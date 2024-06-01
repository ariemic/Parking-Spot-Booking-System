package com.example.parkingSystem.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int bookingId;

    @Column(name = "parking_Id")
    private int parkingId;



    @Column(name="car_registration")
    private String subscriberCarRegistration;

    @Column(name = "booking_date")
    private String bookingDate;


    public int getParkingId() {
        return parkingId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getSubscriberCarRegistration() {
        return subscriberCarRegistration;
    }
}



