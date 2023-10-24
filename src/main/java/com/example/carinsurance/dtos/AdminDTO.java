package com.example.carinsurance.dtos;

import com.example.carinsurance.models.UserAuthentication;
import lombok.Data;

@Data
public class AdminDTO {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private UserAuthentication userAuthentication;
}
