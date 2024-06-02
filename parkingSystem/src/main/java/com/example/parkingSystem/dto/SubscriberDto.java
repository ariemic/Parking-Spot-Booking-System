package com.example.parkingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@AllArgsConstructor
@Data
public class SubscriberDto {
    private String carRegistration;
    private String firstName;
    private String lastName;
}
