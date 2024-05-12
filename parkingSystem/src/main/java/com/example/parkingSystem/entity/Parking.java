package com.example.parkingSystem.entity;

import com.example.parkingSystem.entity.Booking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="parking")
public class Parking {

    @Id
    @Column(name="parking_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int parkingId;

    @Column(name="coordinates")
    private String coordinates;

    @Column(name="address")
    private String address;

    @Column(name = "max_slots")
    private int maxSlots;

    @OneToMany(mappedBy = "parkingId")
    @JsonIgnore
    private List<Booking> bookingList;
}
