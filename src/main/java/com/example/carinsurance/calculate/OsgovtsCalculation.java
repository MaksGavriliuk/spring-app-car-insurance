package com.example.carinsurance.calculate;

import com.example.carinsurance.models.Car;
import com.example.carinsurance.models.User;


public class OsgovtsCalculation implements InsuranceCalculateStrategy {
    @Override
    public double insuranceCalculate(Car car, User user, long daysBetween) {
        return 0;
    }
}
