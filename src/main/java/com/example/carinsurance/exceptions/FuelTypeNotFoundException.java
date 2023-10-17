package com.example.carinsurance.exceptions;

public class FuelTypeNotFoundException extends RuntimeException {
    public FuelTypeNotFoundException(String message) {
        super(message);
    }
}
