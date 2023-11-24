package com.example.carinsurance.calculate;

import com.example.carinsurance.models.UserCar;

public class GreenCardCalculation implements InsuranceCalculateStrategy {
    @Override
    public double insuranceCalculate(UserCar userCar) {
        return 0;
    }
}
