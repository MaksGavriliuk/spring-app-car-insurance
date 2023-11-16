package com.example.carinsurance.repositories;

import com.example.carinsurance.models.Admin;
import com.example.carinsurance.models.InsuranceAgent;
import com.example.carinsurance.models.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InsuranceAgentRepository extends JpaRepository<InsuranceAgent, Integer> {
    Optional<InsuranceAgent> findByUserAuthentication(UserAuthentication userAuthentication);
}
