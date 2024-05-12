package com.example.parkingSystem.validation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CoordinatesValidationTest {

    @Test
    public void testValidCoordinates() {
        assertTrue(CoordinatesValidation.isValidCoordinates("12.34, 34.123"));
        assertTrue(CoordinatesValidation.isValidCoordinates("99, 99.1"));
    }

    @Test
    public void testInvalidCoordinates() {
        assertFalse(CoordinatesValidation.isValidCoordinates("123, 45")); // More than 2 digits
        assertFalse(CoordinatesValidation.isValidCoordinates("12, 345.1")); // More than 2 digits
        assertFalse(CoordinatesValidation.isValidCoordinates("12,34"));   // Missing space
        assertFalse(CoordinatesValidation.isValidCoordinates("12 34"));   // Missing comma
        assertFalse(CoordinatesValidation.isValidCoordinates("12.34"));   // Invalid format
        assertFalse(CoordinatesValidation.isValidCoordinates("ab, cd"));  // Non-numeric characters
        assertFalse(CoordinatesValidation.isValidCoordinates("12,34,"));  // Trailing comma
    }
}
