package com.example.carinsurance.repositories;

import com.example.carinsurance.auth.SearchEngineUserByUserAuthentication;
import com.example.carinsurance.models.Admin;
import com.example.carinsurance.models.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AdminRepository extends JpaRepository<Admin, Integer>, SearchEngineUserByUserAuthentication<Admin> {
    Optional<Admin> findByUserAuthentication(UserAuthentication userAuthentication);

    @Override
    default Optional<Admin> findUserByUserAuthentication(UserAuthentication userAuthentication){
        return findByUserAuthentication(userAuthentication);
    }

}
