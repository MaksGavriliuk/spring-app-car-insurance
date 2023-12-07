package com.example.carinsurance.calculate;


import java.math.BigDecimal;

public record InsuranceCalculateResponse(BigDecimal amount, BigDecimal payoutAmount) {
}
