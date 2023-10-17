package com.example.carinsurance.config;

import com.example.carinsurance.exceptions.BrandNotFoundException;
import com.example.carinsurance.exceptions.EngineVolumeNotFoundException;
import com.example.carinsurance.exceptions.FuelTypeNotFoundException;
import com.example.carinsurance.exceptions.ModelNotFoundException;
import com.example.carinsurance.exceptions.UserAuthenticationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleBrandNotFoundException(BrandNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleModelNotFoundException(ModelNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleEngineVolumeNotFoundException(EngineVolumeNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleFuelTypeNotFoundException(FuelTypeNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleUserAuthenticationNotFoundException(UserAuthenticationNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

}
