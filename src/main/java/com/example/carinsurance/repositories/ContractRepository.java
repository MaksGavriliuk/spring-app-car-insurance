package com.example.carinsurance.repositories;

import com.example.carinsurance.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
}
