package com.example.carinsurance.dtos;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class CarDTO {
    private int id;
    private int modelId;
    private int engineVolumeId;
    private int fuelTypeId;
    private int productionYear;
    private BigDecimal currentValue;
}
