package com.example.carinsurance.calculate;

import com.example.carinsurance.dtos.CalculationDTO;

public class OsgovtsCalculation implements InsuranceCalculateStrategy {
    @Override
    public double insuranceCalculate(CalculationDTO calculationDTO) {
        return 2;
    }
}
