package com.example.parkingSystem.exceptions;

public class WrongDateFormatException extends Exception {
    public WrongDateFormatException() {
        super("Podano zły format daty");
    }
}
