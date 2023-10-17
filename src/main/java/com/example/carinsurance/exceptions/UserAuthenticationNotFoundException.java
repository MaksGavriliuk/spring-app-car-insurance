package com.example.carinsurance.exceptions;

public class UserAuthenticationNotFoundException extends RuntimeException {
    public UserAuthenticationNotFoundException(String message) {
        super(message);
    }
}
