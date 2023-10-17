package com.example.carinsurance.dtos;


import lombok.Data;

@Data
public class FeedbackDTO {
    private int id;
    private String feedback;
    private int numberOfStars;
    private int userId;
}
