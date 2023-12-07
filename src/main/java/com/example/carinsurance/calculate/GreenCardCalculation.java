package com.example.carinsurance.calculate;

import com.example.carinsurance.exceptions.FuelTypeException;
import com.example.carinsurance.models.Car;
import com.example.carinsurance.models.User;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class GreenCardCalculation implements InsuranceCalculateStrategy {
    @Override
    public InsuranceCalculateResponse insuranceCalculate(Car car, User user, long daysBetween) {
        BigDecimal premium = calculatePremium(car, user, daysBetween).setScale(2, RoundingMode.HALF_UP);
        BigDecimal insuranceAmount = calculateInsuranceAmount(premium).setScale(2, RoundingMode.HALF_UP);
        return new InsuranceCalculateResponse(premium, insuranceAmount);
    }

    private BigDecimal calculatePremium(Car car, User user, long daysBetween) {
        return InsuranceCoefficients.GREEN_CARD_COEFFICIENT
                .multiply(calculateEngineVolumePremium(car))
                .multiply(calculateEngineVolumePremium(car))
                .multiply(calculateFuelTypePremium(car))
                .multiply(calculateCurrentValuePremium(car))
                .multiply(calculateAgePremium(user))
                .multiply(calculateExperiencePremium(user))
                .multiply(new BigDecimal(daysBetween));
    }

    private BigDecimal calculateEngineVolumePremium(Car car) {
        return car.getEngineVolume().getEngineVolume().multiply(InsuranceCoefficients.ENGINE_VOLUME_COEFFICIENT);
    }

    private BigDecimal calculateFuelTypePremium(Car car) {
        return switch (car.getFuelType().getFuelType()) {
            case "бензин" -> InsuranceCoefficients.PETROL_FUEL_COEFFICIENT;
            case "дизель" -> InsuranceCoefficients.DIESEL_FUEL_COEFFICIENT;
            case "электро" -> InsuranceCoefficients.ELECTRIC_FUEL_COEFFICIENT;
            default -> throw new FuelTypeException("Такого типа топлива не существует");
        };
    }

    private BigDecimal calculateAgePremium(User user) {
        return (user.getAge() > 21) ?
                InsuranceCoefficients.AGE_MORE_THAN_21_COEFFICIENT : InsuranceCoefficients.AGE_LESS_THAN_21_COEFFICIENT;
    }

    private BigDecimal calculateExperiencePremium(User user) {
        return (user.getExperience() > 2) ?
                InsuranceCoefficients.EXPERIENCE_MORE_THAN_2_COEFFICIENT : InsuranceCoefficients.EXPERIENCE_LESS_THAN_2_COEFFICIENT;
    }

    private BigDecimal calculateCurrentValuePremium(Car car) {
        return car.getCurrentValue().multiply(InsuranceCoefficients.CURRENT_VALUE_COEFFICIENT);
    }


    private BigDecimal calculateInsuranceAmount(BigDecimal premium) {
        return premium.multiply(InsuranceCoefficients.BASE_PREMIUM_RATE).multiply(new BigDecimal(100));
    }
}
