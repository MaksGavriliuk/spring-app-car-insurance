package com.example.carinsurance.calculate;

import com.example.carinsurance.models.Car;
import com.example.carinsurance.models.User;


public class CascoCalculation implements InsuranceCalculateStrategy {
    @Override
    public InsuranceCalculateResponse insuranceCalculate(Car car, User user, long daysBetween) {
        return new InsuranceCalculateResponse(1, 2);
    }
}
