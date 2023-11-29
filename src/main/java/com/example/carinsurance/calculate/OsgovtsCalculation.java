package com.example.carinsurance.calculate;

import com.example.carinsurance.dtos.CalculationDTO;
import com.example.carinsurance.models.Car;
import com.example.carinsurance.models.User;

import java.util.Date;
import java.util.List;

public class OsgovtsCalculation implements InsuranceCalculateStrategy {
    @Override
    public double insuranceCalculate(Car car, User user, List<Date> dates) {
        return 0;
    }
}
