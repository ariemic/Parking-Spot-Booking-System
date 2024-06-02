package com.example.parkingSystem.services;

import com.example.parkingSystem.dao.BookingRepository;
import com.example.parkingSystem.entity.Booking;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Random;

@Service
public class BookingGeneratorService {

    @Autowired
    private BookingRepository bookingRepository;

    private static final String[] SUBSCRIBERS = {
            "ABC123", "DEF456", "GHI789", "JKL101", "MNO112", "PQR131",
            "STU415", "VWX161", "YZA718", "BCD192", "EFG213", "HIJ324",
            "KLM435", "NOP546", "QRS657", "TUV768", "WXY879", "ZAB980",
            "BCD101", "DEF212", "GHI323", "JKL434", "MNO545", "PQR656",
            "STU767", "VWX878", "YZA989", "BCD100", "EFG211", "HIJ322",
            "KLM433", "NOP544", "QRS655", "TUV766", "WXY877", "ZAB988",
            "BCD109", "DEF210", "GHI321", "JKL432", "MNO543", "PQR654"
    };
    private static final int[] PARKING_IDS = {1, 2, 3, 4, 5, 6};

    @PostConstruct
    public void generateBookings() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Random random = new Random();

        for (int i = 0; i < 300; i++) {
            String carRegistration = SUBSCRIBERS[random.nextInt(SUBSCRIBERS.length)];
            int parkingId = PARKING_IDS[random.nextInt(PARKING_IDS.length)];
            Date bookingDate = generateRandomDate("2024-06-01", "2024-12-31");

            Booking booking = new Booking();
            booking.setSubscriberCarRegistration(carRegistration);
            booking.setParkingId(parkingId);
            booking.setBookingDate(String.valueOf(bookingDate));

            bookingRepository.save(booking);
        }

        System.out.println("Bookings generated successfully.");
    }


    private Date generateRandomDate(String startDate, String endDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date start = dateFormat.parse(startDate);
            Date end = dateFormat.parse(endDate);

            long randomDate = start.getTime() + (long) (Math.random() * (end.getTime() - start.getTime()));
            return new java.sql.Date(randomDate); // Use java.sql.Date
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
