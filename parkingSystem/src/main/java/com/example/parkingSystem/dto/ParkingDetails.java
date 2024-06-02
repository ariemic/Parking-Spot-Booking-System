package com.example.parkingSystem.dto;

import com.example.parkingSystem.entity.Parking;
import lombok.Data;

@Data
public class ParkingDetails {

    private int parkingId;
    private String coordinates;
    private String address;
    private int maxSlots;
    private int freeSlots;
    private String percentageParkingLoad;

    public ParkingDetails(Parking parking, int freeSlots) {
        this.parkingId = parking.getParkingId();
        this.coordinates = parking.getCoordinates();
        this.address = parking.getAddress();
        this.maxSlots = parking.getMaxSlots();
        this.freeSlots = freeSlots;
        this.percentageParkingLoad = calculatePercentageParkingLoad();
    }

    private String calculatePercentageParkingLoad() {
        double parkingLoad = ((double)(maxSlots - freeSlots) / maxSlots) * 100;
        return String.format("%.0f%%", parkingLoad);
    }

}
