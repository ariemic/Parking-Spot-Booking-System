package com.example.parkingSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Parking parking;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="car_registration")
    @JsonIgnore
    private Subscriber subscriber;

    @Column(name = "booking_date")
    private String bookingDate;


    @JsonIgnore
    public Parking getParking() {
        return parking;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public int getBookingId() {
        return bookingId;
    }



    public Subscriber getSubscriber() {
        return subscriber;
    }
}



