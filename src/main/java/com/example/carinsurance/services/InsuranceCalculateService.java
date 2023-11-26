package com.example.carinsurance.services;

import com.example.carinsurance.calculate.InsuranceCalculateFactory;
import com.example.carinsurance.dtos.CalculationDTO;
import com.example.carinsurance.dtos.UserCarDTO;
import com.example.carinsurance.models.UserCar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceCalculateService {

    private final InsuranceCalculateFactory insuranceCalculateFactory;

    public double calculate(CalculationDTO calculationDTO, String calculationType){
        return insuranceCalculateFactory.createInsuranceCalculation(calculationType)
                .insuranceCalculate(calculationDTO);
    }

}
