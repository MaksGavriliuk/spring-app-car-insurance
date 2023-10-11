package com.example.carinsurance.repositories;

import com.example.carinsurance.models.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserAuthenticationRepository extends JpaRepository<UserAuthentication, Integer> {
    UserAuthentication findByLogin(String login);
}
