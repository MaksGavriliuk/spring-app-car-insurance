package com.example.carinsurance.exceptions;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String message){
        super(message);
    }
}
