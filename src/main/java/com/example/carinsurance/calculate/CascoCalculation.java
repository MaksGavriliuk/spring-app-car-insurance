package com.example.carinsurance.calculate;

import com.example.carinsurance.dtos.CalculationDTO;

public class CascoCalculation implements InsuranceCalculateStrategy {
    @Override
    public double insuranceCalculate(CalculationDTO calculationDTO) {
        return 0;
//        return DaysBetweenDates.daysBetweenDates(
//                userCarDTO.getDates().get(0),
//                userCarDTO.getDates().get(1)
//                );
    }
}
