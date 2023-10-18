package com.example.carinsurance.dtos;


import lombok.Data;

import java.math.BigDecimal;


@Data
public class InsuranceAgentDTO {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private String sex;
    private int age;
    private BigDecimal profit;
    private int userAuthenticationId;
}
