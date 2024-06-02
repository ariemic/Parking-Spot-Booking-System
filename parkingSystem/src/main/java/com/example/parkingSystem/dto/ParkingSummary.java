package com.example.parkingSystem.dto;

import com.example.parkingSystem.dao.ParkingRepository;
import com.example.parkingSystem.dao.SubscriberRepository;
import com.example.parkingSystem.entity.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



public class ParkingSummary {
    private Parking parking;
    private int totalCaluclatedDays;
    private int totalParkedSum;
    private int AvarageDailyParkedSum;
    private int maxBookedSlots;
    private int AvarageDailyParkedPercentage;
    private int maxDailyBookedPercentage;

    public ParkingSummary(Parking parking) {
        this.parking = parking;
    }

    public Parking getParking() {
        return parking;
    }

    public int getTotalCaluclatedDays() {
        return totalCaluclatedDays;
    }

    public void setTotalCaluclatedDays(int totalCaluclatedDays) {
        this.totalCaluclatedDays = totalCaluclatedDays;
    }

    public int getTotalParkedSum() {
        return totalParkedSum;
    }

    public void setTotalParkedSum(int totalParkedSum) {
        this.totalParkedSum = totalParkedSum;
    }

    public int getAvarageDailyParkedSum() {
        return AvarageDailyParkedSum;
    }

    public void setAvarageDailyParkedSum(int avarageDailyParkedSum) {
        AvarageDailyParkedSum = avarageDailyParkedSum;
    }

    public int getMaxBookedSlots() {
        return maxBookedSlots;
    }

    public void setMaxBookedSlots(int maxBookedSlots) {
        this.maxBookedSlots = maxBookedSlots;
    }

    public int getAvarageDailyParkedPercentage() {
        return AvarageDailyParkedPercentage;
    }

    public void setAvarageDailyParkedPercentage(int avarageDailyParkedPercentage) {
        AvarageDailyParkedPercentage = avarageDailyParkedPercentage;
    }

    public int getMaxDailyBookedPercentage() {
        return maxDailyBookedPercentage;
    }

    public void setMaxDailyBookedPercentage(int maxDailyBookedPercentage) {
        this.maxDailyBookedPercentage = maxDailyBookedPercentage;
    }
}
