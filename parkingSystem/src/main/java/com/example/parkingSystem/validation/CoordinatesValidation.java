package com.example.parkingSystem.validation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoordinatesValidation {
    private static final Pattern COORDINATES_PATTERN = Pattern.compile("\\d{2}, \\d{2}");

    public static boolean isValidCoordinates(String coords){
        Matcher matcher = COORDINATES_PATTERN.matcher(coords);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String testCoordinates1 = "12,34";
        String testCoordinates2 = "1234"; // niepoprawny format

        System.out.println("Test 1: " + testCoordinates1 + " - " + isValidCoordinates(testCoordinates1));
        System.out.println("Test 2: " + testCoordinates2 + " - " + isValidCoordinates(testCoordinates2));
    }
}
