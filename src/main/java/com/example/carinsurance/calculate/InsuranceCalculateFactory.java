package com.example.carinsurance.calculate;

import org.springframework.stereotype.Component;

@Component
public class InsuranceCalculateFactory {
    public InsuranceCalculateStrategy createInsuranceCalculation(String calculationType) {
        return switch (calculationType) {
            case "osgovts" -> new OsgovtsCalculation();
            case "casko" -> new CascoCalculation();
            case "green-card" -> new GreenCardCalculation();
            default -> null;
        };
    }
}
