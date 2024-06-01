package com.example.parkingSystem.controllers;

import com.example.parkingSystem.config.DataBookingController;
import com.example.parkingSystem.dao.BookingRepository;
import com.example.parkingSystem.entity.Booking;
import com.example.parkingSystem.services.ParkingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//manualnie sprawdzone - działają ^ parkingi tez działają
@WebMvcTest(DataBookingController.class)
@AutoConfigureMockMvc
public class DataBookingControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookingRepository bookingRepository;

    @MockBean
    private ParkingService parkingService;

//    @Test
//    public void testCreateBooking() throws Exception{
//        Booking booking = new Booking();
//        booking.setParkingId(1);
//        booking.setSubscriberCarRegistration("ABC123");
//        booking.setBookingDate("2024-06-01");
//
//        //mockowanie
//        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
//
//        mockMvc.perform(post("/bookings")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(booking)))
//                .andExpect(status().isOk());
//
//
//    }




}
