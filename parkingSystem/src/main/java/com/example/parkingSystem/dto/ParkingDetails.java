package com.example.parkingSystem.dto;

import lombok.Data;

@Data
public class ParkingDetails {
    private int parkingId;
    private String coordinates;
    private String address;
    private int maxSlots;
    private int freeSlots;
    private int percentageParkingLoad;

}
