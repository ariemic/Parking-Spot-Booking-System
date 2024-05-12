package com.example.parkingSystem.dao;

import com.example.parkingSystem.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParkingRepository extends JpaRepository<Parking, Integer> {
}

