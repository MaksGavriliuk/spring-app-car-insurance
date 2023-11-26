package com.example.carinsurance.calculate;

import com.example.carinsurance.dtos.CalculationDTO;

public interface InsuranceCalculateStrategy {
    double insuranceCalculate(CalculationDTO calculationDTO);
}
