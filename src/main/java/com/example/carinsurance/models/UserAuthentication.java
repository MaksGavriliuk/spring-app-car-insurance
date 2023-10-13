package com.example.carinsurance.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Data
@Entity
@Table(name = "user_authentications")
public class UserAuthentication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    @OneToOne(mappedBy = "userAuthentication", cascade = CascadeType.ALL)
    private Admin admin;


    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public boolean isPasswordValid(String password) {
        return new BCryptPasswordEncoder().matches(password, this.password);
    }

}