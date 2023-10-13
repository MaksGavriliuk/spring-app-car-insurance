package com.example.carinsurance.repositories;

import com.example.carinsurance.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
