package com.example.carinsurance.calculate;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class PremiumCalculation {
    private Integer insuranceAgentId;
    private BigDecimal amount;
    private BigDecimal percent;
}
