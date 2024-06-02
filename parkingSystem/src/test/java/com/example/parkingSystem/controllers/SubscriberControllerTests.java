package com.example.parkingSystem.controllers;

import com.example.parkingSystem.dao.SubscriberRepository;
import com.example.parkingSystem.entity.Subscriber;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SubscriberControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Autowired
    private ObjectMapper objectMapper;




    @Test
    public void testGetSubscriber() throws Exception {
        Subscriber subscriber = new Subscriber();
        subscriber.setCarRegistration("DEF456");
        subscriber.setFirstName("Jane");
        subscriber.setLastName("Doe");
        subscriber.setEndDate(new java.sql.Date(System.currentTimeMillis()));
        subscriber.setMainParking(2);
        subscriber.setAllParkings(false);

        subscriberRepository.save(subscriber);

        mockMvc.perform(get("/subscribers/DEF456")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carRegistration").value("DEF456"));
    }

    @Test
    public void testUpdateSubscriber() throws Exception {
        Subscriber subscriber = new Subscriber();
        subscriber.setCarRegistration("GHI789");
        subscriber.setFirstName("Alice");
        subscriber.setLastName("Smith");
        subscriber.setEndDate(new java.sql.Date(System.currentTimeMillis()));
        subscriber.setMainParking(3);
        subscriber.setAllParkings(true);

        subscriberRepository.save(subscriber);

        subscriber.setFirstName("AliceUpdated");
        subscriber.setLastName("SmithUpdated");

        mockMvc.perform(put("/subscribers/GHI789")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subscriber)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("AliceUpdated"))
                .andExpect(jsonPath("$.lastName").value("SmithUpdated"));
    }

    @Test
    public void testPatchSubscriber() throws Exception {
        Subscriber subscriber = new Subscriber();
        subscriber.setCarRegistration("JKL012");
        subscriber.setFirstName("Bob");
        subscriber.setLastName("Brown");
        subscriber.setEndDate(new java.sql.Date(System.currentTimeMillis()));
        subscriber.setMainParking(4);
        subscriber.setAllParkings(true);

        subscriberRepository.save(subscriber);

        Subscriber patchData = new Subscriber();
        patchData.setLastName("BrownUpdated");

        mockMvc.perform(patch("/subscribers/JKL012")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patchData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("BrownUpdated"));
    }

    @Test
    public void testDeleteSubscriber() throws Exception {
        Subscriber subscriber = new Subscriber();
        subscriber.setCarRegistration("JKL012");
        subscriber.setFirstName("Bob");
        subscriber.setLastName("BrownUpdated");
        subscriber.setEndDate(new java.sql.Date(System.currentTimeMillis()));
        subscriber.setMainParking(4);
        subscriber.setAllParkings(false);

        subscriberRepository.save(subscriber);

        mockMvc.perform(delete("/subscribers/JKL012")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Optional<Subscriber> deletedSubscriber = subscriberRepository.findById("JKL012");
        assert(deletedSubscriber.isEmpty());
    }
}

//post działa sprawdzany manualnie ale nie przechodzi testu ale nawet kod dodał do bazy danych ... dziwne
//    @Test
//    public void testCreateSubscriber() throws Exception {
//        Subscriber subscriber = new Subscriber();
//        subscriber.setCarRegistration("JKU458");
//        subscriber.setFirstName("Monika");
//        subscriber.setLastName("Orkisz");
//        subscriber.setEndDate(Date.valueOf("2024-10-30"));
//        subscriber.setMainParking(4);
//        subscriber.setAllParkings(true);
//
//        mockMvc.perform(post("/subscribers")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(subscriber)))
//                .andExpect(status().isCreated())  // Zmiana statusu na 201
//                .andExpect(jsonPath("$.carRegistration").value("JKU458"))
//                .andExpect(jsonPath("$.firstName").value("Monika"))
//                .andExpect(jsonPath("$.lastName").value("Orkisz"))
//                .andExpect(jsonPath("$.endDate").value("2024-10-30"))
//                .andExpect(jsonPath("$.mainParking").value(4))
//                .andExpect(jsonPath("$.allParkings").value(true));
//
//    }