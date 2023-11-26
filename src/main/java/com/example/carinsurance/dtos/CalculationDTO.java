package com.example.carinsurance.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CalculationDTO {
    private int id;
    private int userId;
    private int carId;
    List<String> dates;
}
