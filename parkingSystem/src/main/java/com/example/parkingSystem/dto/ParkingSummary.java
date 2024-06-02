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
    private float AvarageDailyParkedSum;
    private int maxBookedSlots;
    private float AvarageDailyParkedPercentage;
    private float maxDailyBookedPercentage;

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

    public float getAvarageDailyParkedSum() {
        return AvarageDailyParkedSum;
    }

    public void setAvarageDailyParkedSum(float avarageDailyParkedSum) {
        AvarageDailyParkedSum = avarageDailyParkedSum;
    }

    public int getMaxBookedSlots() {
        return maxBookedSlots;
    }

    public void setMaxBookedSlots(int maxBookedSlots) {
        this.maxBookedSlots = maxBookedSlots;
    }

    public float getAvarageDailyParkedPercentage() {
        return AvarageDailyParkedPercentage;
    }

    public void setAvarageDailyParkedPercentage(float avarageDailyParkedPercentage) {
        AvarageDailyParkedPercentage = avarageDailyParkedPercentage;
    }

    public float getMaxDailyBookedPercentage() {
        return maxDailyBookedPercentage;
    }

    public void setMaxDailyBookedPercentage(float maxDailyBookedPercentage) {
        this.maxDailyBookedPercentage = maxDailyBookedPercentage;
    }
}
