package com.example.parkingSystem.exceptions;

public class LicenseExpiredException extends Exception{
    public LicenseExpiredException() {
        super("Licencja podanego subskrybenta przedawniła się");
    }
}
