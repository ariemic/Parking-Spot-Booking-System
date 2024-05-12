package com.example.parkingSystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "subscribers")
public class Subscriber {
    @Id
    @Column(name = "car_registration", nullable = false)
    private String carRegistrationNumber;


    @OneToMany(mappedBy = "subscriber")
    @JsonManagedReference
    private List<Booking> subscriberBookings;



    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "main_parking")
    private Integer mainParking;

    @Column(name = "all_parkings")
    private boolean allParkings; //0 - false, 1 - true
}


