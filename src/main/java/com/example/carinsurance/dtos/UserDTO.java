package com.example.carinsurance.dtos;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private String sex;
    private int age;
    private int experience;
    private String login;
    private String password;
}
