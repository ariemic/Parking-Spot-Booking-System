package com.example.parkingSystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "subscribers")

public class Subscriber {

    @Id
    @Column(name = "car_registration", nullable = false)
    @Pattern(regexp = "\\p{L}+ \\d+")
    private String carRegistration;





    @Column(name = "first_name")
    @NotBlank(message = "your name can't be empty")
    private String firstName;

    @NotBlank(message = "your surname can't be empty")
    @Column(name = "last_name")
    private String lastName;


    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "main_parking")
    private Integer mainParking;

    @Column(name = "all_parkings")
    private boolean allParkings;
}


