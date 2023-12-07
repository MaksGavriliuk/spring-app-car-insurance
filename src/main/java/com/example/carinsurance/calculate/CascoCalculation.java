package com.example.carinsurance.calculate;

import com.example.carinsurance.models.Car;
import com.example.carinsurance.models.User;

import java.math.BigDecimal;

public class CascoCalculation implements InsuranceCalculateStrategy {
    private static final double BASE_PREMIUM_RATE = 0.05;

    @Override
    public InsuranceCalculateResponse insuranceCalculate(Car car, User user, long daysBetween) {
        BigDecimal premium = calculatePremium(car, user, daysBetween);
        BigDecimal insuranceAmount = calculateInsuranceAmount(car, premium);
        return new InsuranceCalculateResponse(premium, insuranceAmount);
    }

    private BigDecimal calculatePremium(Car car, User user, long daysBetween) {
        BigDecimal premium = BASE_PREMIUM_RATE;

        // Расчет премии на основе параметров машины, пользователя и количества дней

        // Расчет премии, зависящей от объема двигателя
        BigDecimal engineVolumePremium = car.getEngineVolume().multiply(new BigDecimal("0.001"));
        premium = premium.add(engineVolumePremium);

        // Расчет премии, зависящей от типа топлива
        if (car.getFuelType().equals("Diesel")) {
            BigDecimal dieselFuelPremium = new BigDecimal("0.02");
            premium = premium.add(dieselFuelPremium);
        }

        // Расчет премии, зависящей от бренда машины
        // ... реализуйте логику расчета премии на основе бренда машины ...

        // Расчет премии, зависящей от возраста водителя
        // ... реализуйте логику расчета премии на основе возраста водителя ...

        // Расчет премии, зависящей от стажа водителя
        // ... реализуйте логику расчета премии на основе стажа водителя ...

        // Расчет премии, зависящей от количества дней
        BigDecimal daysPremium = new BigDecimal(daysBetween).multiply(new BigDecimal("0.01"));
        premium = premium.add(daysPremium);

        return premium;
    }

    private BigDecimal calculateInsuranceAmount(Car car, BigDecimal premium) {
        // Расчет страховой суммы на основе премии и других параметров машины

        // ... реализуйте логику расчета страховой суммы на основе премии и других параметров ...

        return insuranceAmount;
    }
}