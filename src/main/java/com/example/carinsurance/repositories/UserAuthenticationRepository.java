package com.example.carinsurance.repositories;

import com.example.carinsurance.models.UserAuthentication;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.naming.InsufficientResourcesException;

public interface UserAuthenticationRepository extends JpaRepository<UserAuthentication, Integer> {
}
