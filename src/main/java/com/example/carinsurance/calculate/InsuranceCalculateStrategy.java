package com.example.carinsurance.calculate;

import com.example.carinsurance.dtos.CalculationDTO;
import com.example.carinsurance.models.Car;
import com.example.carinsurance.models.User;

import java.util.Date;
import java.util.List;

public interface InsuranceCalculateStrategy {
    InsuranceCalculateResponse insuranceCalculate(Car car, User user, long daysBetween);
}
