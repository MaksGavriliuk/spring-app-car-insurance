package com.example.carinsurance.dtos;

import com.example.carinsurance.models.UserAuthentication;
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
    private UserAuthentication userAuthentication;
}
