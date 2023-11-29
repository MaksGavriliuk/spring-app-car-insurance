package com.example.carinsurance.services;

import com.example.carinsurance.calculate.DaysBetweenDates;
import com.example.carinsurance.calculate.InsuranceCalculateFactory;
import com.example.carinsurance.dtos.CalculationDTO;
import com.example.carinsurance.dtos.UserCarDTO;
import com.example.carinsurance.exceptions.CarException;
import com.example.carinsurance.exceptions.UserException;
import com.example.carinsurance.models.Car;
import com.example.carinsurance.models.User;
import com.example.carinsurance.models.UserCar;
import com.example.carinsurance.repositories.CarRepository;
import com.example.carinsurance.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceCalculateService {

    private final InsuranceCalculateFactory insuranceCalculateFactory;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public double calculate(CalculationDTO calculationDTO, String calculationType) {

        Car car = carRepository.findById(calculationDTO.getCarId()).orElseThrow(() -> new CarException("Машины с таким id не существует"));
        User user = userRepository.findById(calculationDTO.getUserId()).orElseThrow(() -> new UserException("Пользователя с таким id не существует"));
        long daysBetween = DaysBetweenDates.daysBetweenDates(
                calculationDTO.getDates().get(0),
                calculationDTO.getDates().get(1)
        );

        return insuranceCalculateFactory.createInsuranceCalculation(calculationType)
                .insuranceCalculate(car, user, daysBetween);

    }

}
