package com.example.carinsurance.auth;


import com.example.carinsurance.models.UserAuthentication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String surname;
    private String name;
    private String patronymic;
    private String sex;
    private int age;
    private int experience;
    private UserAuthentication userAuthentication;
}
