package com.example.parkingSystem.dao;

import com.example.parkingSystem.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, Integer> {
   Parking findByParkingId(int id);

}

