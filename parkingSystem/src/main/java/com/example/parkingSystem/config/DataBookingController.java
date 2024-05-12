package com.example.parkingSystem.config;

import com.example.parkingSystem.dao.ParkingRepository;
import org.springframework.web.bind.annotation.GetMapping;

public class DataBookingController {
    private ParkingRepository parkingRepository;

    public DataBookingController(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }



}
