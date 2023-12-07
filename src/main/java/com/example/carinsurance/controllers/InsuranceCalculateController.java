package com.example.carinsurance.controllers;


import com.example.carinsurance.calculate.InsuranceCalculateResponse;
import com.example.carinsurance.dtos.CalculationDTO;
import com.example.carinsurance.services.InsuranceCalculateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calculate")
public class InsuranceCalculateController {

    private final InsuranceCalculateService insuranceCalculateService;

    @PostMapping
    public ResponseEntity<InsuranceCalculateResponse> calculate(
            @RequestParam(name = "insurance-type") String calculateType,
            @RequestBody CalculationDTO calculationDTO
    ) {
        InsuranceCalculateResponse result = insuranceCalculateService.calculate(calculationDTO, calculateType);
        return ResponseEntity.ok().body(result);
    }

}
