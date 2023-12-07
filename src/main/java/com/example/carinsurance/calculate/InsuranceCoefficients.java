package com.example.carinsurance.calculate;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class InsuranceCoefficients {

    public static final BigDecimal BASE_PREMIUM_RATE = new BigDecimal("1.05");

    public static final BigDecimal ENGINE_VOLUME_COEFFICIENT = new BigDecimal("1.01");

    public static final BigDecimal PETROL_FUEL_COEFFICIENT = new BigDecimal("1.03");

    public static final BigDecimal DIESEL_FUEL_COEFFICIENT = new BigDecimal("1.02");

    public static final BigDecimal ELECTRIC_FUEL_COEFFICIENT = new BigDecimal("1.015");

    public static final BigDecimal OSGOVTS_COEFFICIENT = new BigDecimal("1.04");

    public static final BigDecimal CASKO_COEFFICIENT = new BigDecimal("1.2");

    public static final BigDecimal GREEN_CARD_COEFFICIENT = new BigDecimal("1.1");

    public static final BigDecimal CURRENT_VALUE_COEFFICIENT = new BigDecimal("0.000005");

    public static final BigDecimal AGE_LESS_THAN_21_COEFFICIENT = new BigDecimal("1.02");

    public static final BigDecimal AGE_MORE_THAN_21_COEFFICIENT = new BigDecimal("1.01");

    public static final BigDecimal EXPERIENCE_LESS_THAN_2_COEFFICIENT = new BigDecimal("1.05");

    public static final BigDecimal EXPERIENCE_MORE_THAN_2_COEFFICIENT = new BigDecimal("1.03");

}
