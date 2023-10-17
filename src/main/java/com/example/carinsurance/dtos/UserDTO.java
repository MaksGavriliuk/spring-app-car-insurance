package com.example.carinsurance.dtos;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private int userAuthenticationId;
    private String surname;
    private String name;
    private String patronymic;
    private String sex;
    private int age;
    private int experience;
}
