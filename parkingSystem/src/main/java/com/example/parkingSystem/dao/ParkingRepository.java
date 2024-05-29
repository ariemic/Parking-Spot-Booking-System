package com.example.parkingSystem.dao;

import com.example.parkingSystem.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking, Integer> {
   Parking findByParkingId(int id);

}

