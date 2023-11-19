package com.example.carinsurance.repositories;

import com.example.carinsurance.auth.SearchEngineUserByUserAuthentication;
import com.example.carinsurance.models.Admin;
import com.example.carinsurance.models.InsuranceAgent;
import com.example.carinsurance.models.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("insuranceAgentRepository")
public interface InsuranceAgentRepository extends JpaRepository<InsuranceAgent, Integer>, SearchEngineUserByUserAuthentication<InsuranceAgent> {
    Optional<InsuranceAgent> findByUserAuthentication(UserAuthentication userAuthentication);

    @Override
    default Optional<InsuranceAgent> findUserByUserAuthentication(UserAuthentication userAuthentication){
        return findByUserAuthentication(userAuthentication);
    }

}
