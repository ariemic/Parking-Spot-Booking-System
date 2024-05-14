package com.example.parkingSystem.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidaiton {
    private static DateValidaiton instance;


    private DateValidaiton() {
    }

    public boolean validateDate(String date){
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);

        return matcher.matches();
    }

    public static DateValidaiton getInstance(){
        if(instance == null){
            instance = new DateValidaiton();
        }
        return instance;
    }

}
