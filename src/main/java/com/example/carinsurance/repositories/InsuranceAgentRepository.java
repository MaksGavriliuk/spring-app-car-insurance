package com.example.carinsurance.repositories;

import com.example.carinsurance.models.InsuranceAgent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceAgentRepository extends JpaRepository<InsuranceAgent, Integer> {
}
