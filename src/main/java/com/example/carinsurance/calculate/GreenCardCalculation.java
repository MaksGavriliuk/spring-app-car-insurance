package com.example.carinsurance.calculate;

import com.example.carinsurance.dtos.CalculationDTO;

public class GreenCardCalculation implements InsuranceCalculateStrategy {
    @Override
    public double insuranceCalculate(CalculationDTO calculationDTO) {
        return 1;
    }
}
