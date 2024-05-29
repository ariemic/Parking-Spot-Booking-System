package com.example.parkingSystem.exceptions;

public class SubscriberNotFoundException extends Exception {
    public SubscriberNotFoundException() {
        super("Nie znaleziono użytwkonika o takim numerze rejestracyjnym");
    }
}
