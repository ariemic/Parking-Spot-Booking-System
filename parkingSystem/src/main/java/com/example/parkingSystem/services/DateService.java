package com.example.parkingSystem.services;

import com.example.parkingSystem.validation.DateValidation;
import org.springframework.stereotype.Service;

@Service
public class DateService {
    //todo do zaimplementowania w DataRestParkingController zeby nie powtarzac sie z walidacja date -> DataRestController może
    public boolean validateDate(String date){
        DateValidation dateValidation = DateValidation.getInstance();
        return dateValidation.validateDate(date);
    }

}
