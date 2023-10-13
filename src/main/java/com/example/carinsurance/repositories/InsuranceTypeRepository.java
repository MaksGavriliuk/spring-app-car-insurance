package com.example.carinsurance.repositories;

import com.example.carinsurance.models.InsuranceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceTypeRepository extends JpaRepository<InsuranceType, Integer> {
}
