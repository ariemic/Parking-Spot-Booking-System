package com.example.parkingSystem.exceptions;

public class SubscriberNotFoundException extends RuntimeException {
    public SubscriberNotFoundException() {
        super("Nie znaleziono subskrybenta o podanej rejestracji");
    }
}
