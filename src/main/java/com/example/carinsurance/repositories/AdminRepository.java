package com.example.carinsurance.repositories;

import com.example.carinsurance.auth.SearchEngineUserByUserAuthentication;
import com.example.carinsurance.models.Admin;
import com.example.carinsurance.models.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer>, SearchEngineUserByUserAuthentication<Admin> {
    Optional<Admin> findByUserAuthentication(UserAuthentication userAuthentication);

    @Override
    default Optional<Admin> findUserByUserAuthentication(UserAuthentication userAuthentication){
        return findByUserAuthentication(userAuthentication);
    }

}
