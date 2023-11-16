package com.example.carinsurance.repositories;

import com.example.carinsurance.models.Admin;
import com.example.carinsurance.models.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByUserAuthentication(UserAuthentication userAuthentication);
}
