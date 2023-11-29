package com.example.carinsurance.calculate;

import com.example.carinsurance.models.Car;
import com.example.carinsurance.models.User;


public class GreenCardCalculation implements InsuranceCalculateStrategy {
    @Override
    public double insuranceCalculate(Car car, User user, long daysBetween) {
        return daysBetween;
    }
}
