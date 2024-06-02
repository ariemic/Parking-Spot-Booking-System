package com.example.parkingSystem.dto;

import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
public class ParkingSubscribers {
    private int parkingId;
    private String coordinates;
    private String address;
    private List<SubscriberDto> subscribers;


}
