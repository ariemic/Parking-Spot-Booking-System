package com.example.parkingSystem.services;

import com.example.parkingSystem.dao.ParkingRepository;
import com.example.parkingSystem.dto.ParkingSummary;
import com.example.parkingSystem.entity.Parking;
import com.example.parkingSystem.exceptions.WrongDateFormatException;
import com.example.parkingSystem.validation.DateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;


@Service
public class ParkingSummaryService {
    private final ParkingRepository parkingRepository;
    private final ParkingService parkingService;
    @Autowired
    public ParkingSummaryService(ParkingRepository parkingRepository, ParkingService parkingService) {
        this.parkingRepository = parkingRepository;
        this.parkingService = parkingService;
    }

    private int max(int a, int b){
        if(a>b){
            return a;
        }
        return b;
    }

    private float[] calculateStats(String date1, String date2, Parking parking){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(date1, formatter);
        LocalDate endDate = LocalDate.parse(date2, formatter);
        int booked_counter = 0;
        int days = 0;
        int max_count = 0;
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
           int booked = parking.getMaxSlots() - parkingService.numberOfFreeSlots(
                   parking.getParkingId(), date.format(formatter));
//            System.out.println(parkingService.numberOfFreeSlots(parking.getParkingId(), date.format(formatter)));
            booked_counter+= booked;
            max_count = max(max_count, booked);
            days+=1;

        }
        float daily_percentage = 0;
        float max_daily_percentage = 0;
        if(days!=0 && parking.getMaxSlots()!=0){
             daily_percentage = (float) booked_counter/(days * parking.getMaxSlots());
             max_daily_percentage = (float) max_count/parking.getMaxSlots() * 100;
        }

        float[] cntdays = new float[5];
        cntdays[0] = booked_counter;
        cntdays[1] = days;
        cntdays[2] = max_count;
        cntdays[3] = daily_percentage;
        cntdays[4] = max_daily_percentage;

        return cntdays;

    }


    public List<ParkingSummary> parkingSummaries(String date1, String date2) throws Exception{
        DateValidation dateValidation = DateValidation.getInstance();
        if(!dateValidation.validateDate(date1) || !dateValidation.validateDate(date2)){
            throw new WrongDateFormatException();
        }

        List<Parking> listOfParkings = parkingRepository.findAll();
        List<ParkingSummary> parkingSummaries = new LinkedList<>();
        for(Parking parking: listOfParkings){
            ParkingSummary parkingToAdd = new ParkingSummary(parking);
             float[] cntdays = calculateStats(date1,date2,parking);
             int bookedCounter = (int) cntdays[0];
             int days = (int) cntdays[1];
             int maxBooked = (int) cntdays[2];
             float dailyPercentage = cntdays[3];
             float maxDailyPercentage = cntdays[4];
             float avarageDailyParkedSum = (float) bookedCounter/days;
             parkingToAdd.setAvarageDailyParkedSum(avarageDailyParkedSum);
             parkingToAdd.setTotalParkedSum(bookedCounter);
             parkingToAdd.setTotalCaluclatedDays(days);
             parkingToAdd.setMaxDailyBookedPercentage(maxDailyPercentage);
             parkingToAdd.setMaxBookedSlots(maxBooked);
             parkingToAdd.setAvarageDailyParkedPercentage(dailyPercentage);

             parkingSummaries.add(parkingToAdd);

        }

        return parkingSummaries;

    }
}
