package com.example.carinsurance.calculate;

import com.example.carinsurance.models.UserCar;

public class OsgovtsCalculation implements InsuranceCalculateStrategy {
    @Override
    public double insuranceCalculate(UserCar userCar) {
        return 0;
    }
}
