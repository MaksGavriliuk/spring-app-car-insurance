package com.example.carinsurance.calculate;

import com.example.carinsurance.models.UserCar;

public interface InsuranceCalculateStrategy {
    double insuranceCalculate(UserCar userCar);
}
