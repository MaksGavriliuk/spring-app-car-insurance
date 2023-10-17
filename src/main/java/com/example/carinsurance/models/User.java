package com.example.carinsurance.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_authentication_id")
    private UserAuthentication userAuthentication;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "sex")
    private String sex;

    @Column(name = "age")
    private int age;

    @Column(name = "experience")
    private int experience;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

}