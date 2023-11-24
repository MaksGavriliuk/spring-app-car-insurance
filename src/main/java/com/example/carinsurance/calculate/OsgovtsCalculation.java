package com.example.carinsurance.calculate;

import com.example.carinsurance.dtos.UserCarDTO;

public class OsgovtsCalculation implements InsuranceCalculateStrategy {
    @Override
    public double insuranceCalculate(UserCarDTO userCarDTO) {
        return 2;
    }
}
