package com.example.carinsurance.dtos;

import lombok.Data;

@Data
public class AdminDTO {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private int userAuthenticationId;
}
