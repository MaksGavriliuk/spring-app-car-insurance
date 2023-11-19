package com.example.carinsurance.repositories;

import com.example.carinsurance.auth.SearchEngineUserByUserAuthentication;
import com.example.carinsurance.models.Admin;
import com.example.carinsurance.models.InsuranceAgent;
import com.example.carinsurance.models.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InsuranceAgentRepository extends JpaRepository<InsuranceAgent, Integer>, SearchEngineUserByUserAuthentication<InsuranceAgent> {
    Optional<InsuranceAgent> findByUserAuthentication(UserAuthentication userAuthentication);

    @Override
    default Optional<InsuranceAgent> findUserByUserAuthentication(UserAuthentication userAuthentication){
        return findByUserAuthentication(userAuthentication);
    }

}
