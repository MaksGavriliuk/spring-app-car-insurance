package com.example.carinsurance.calculate;

import com.example.carinsurance.dtos.UserCarDTO;

public class GreenCardCalculation implements InsuranceCalculateStrategy {
    @Override
    public double insuranceCalculate(UserCarDTO userCarDTO) {
        return 1;
    }
}
