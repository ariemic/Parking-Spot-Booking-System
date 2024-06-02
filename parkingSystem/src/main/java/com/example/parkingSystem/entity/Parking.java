package com.example.parkingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.security.PrivilegedAction;
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

    //address like {ul.} Mazowiecka number, city
    @Column(name="address")
    @Pattern(regexp = "(ul\\. )?\\p{L}+ \\d+, \\p{L}+", message = "enter address as street number, city")
    private String address;

    @Column(name = "max_slots")
    @Min(value = 0, message = "maximum number of slots must be non-negative number")
    private int maxSlots;

    @Transient
    @Min(value = 0, message = "minimum number of slots must be non-negative number")
    private int freeSlots;

    @PrePersist
    public void initFreeSlots() {
        this.freeSlots = this.maxSlots;
    }

}
