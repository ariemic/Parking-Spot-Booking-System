package com.example.parkingSystem.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidation {
    private static DateValidation instance;


    private DateValidation() {
    }

    public boolean validateDate(String date){
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);

        return matcher.matches();
    }

    public static DateValidation getInstance(){
        if(instance == null){
            instance = new DateValidation();
        }
        return instance;
    }

}
