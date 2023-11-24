package com.example.carinsurance.calculate;

import com.example.carinsurance.dtos.UserCarDTO;

public interface InsuranceCalculateStrategy {
    double insuranceCalculate(UserCarDTO userCarDTO);
}
