package com.example.carinsurance.controllers;


import com.example.carinsurance.models.UserCar;
import com.example.carinsurance.services.InsuranceCalculateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calculate")
public class InsuranceCalculateController {

    private final InsuranceCalculateService insuranceCalculateService;

    @PostMapping
    public ResponseEntity<Double> calculate(
            @PathVariable(name = "calculate-type") String calculateType,
            @RequestBody UserCar userCar
    ) {
        return ResponseEntity.ok().body(insuranceCalculateService.calculate(userCar, calculateType));
    }


}
