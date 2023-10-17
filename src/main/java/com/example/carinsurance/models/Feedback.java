package com.example.carinsurance.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@Entity
@Table(name = "feedbacks")
@AllArgsConstructor
@RequiredArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "feedback", columnDefinition = "TEXT")
    private String feedback;

    @Column(name = "number_of_stars")
    private int numberOfStars;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
