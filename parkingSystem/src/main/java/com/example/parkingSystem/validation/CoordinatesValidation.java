package com.example.parkingSystem.validation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoordinatesValidation {
    private static final Pattern COORDINATES_PATTERN = Pattern.compile("\\d{2}(\\.\\d+)?, \\d{2}(\\.\\d+)?");

    public static boolean isValidCoordinates(String coords){
        Matcher matcher = COORDINATES_PATTERN.matcher(coords);
        return matcher.matches();
    }

}

