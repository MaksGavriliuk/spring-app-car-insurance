package com.example.carinsurance.calculate;

import com.example.carinsurance.dtos.UserCarDTO;

public class CascoCalculation implements InsuranceCalculateStrategy {
    @Override
    public double insuranceCalculate(UserCarDTO userCarDTO) {
        return 0;
    }
}
