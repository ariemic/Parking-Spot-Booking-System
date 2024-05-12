package com.example.parkingSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Entity
@Data
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int bookingId;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "parking_Id")
    private Parking parkingId;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="car_registration")
    private Subscriber subscriber;

    @Column(name = "booking_date")
    private String bookingDate;

    public Parking getParking() {
        return parkingId;
    }

    public String getBookingDate() {
        return bookingDate;
    }
}



