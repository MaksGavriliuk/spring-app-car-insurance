package com.example.carinsurance.dtos;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class ModelDTO {
    private int id;
    private String model;
    private int brandId;
}
