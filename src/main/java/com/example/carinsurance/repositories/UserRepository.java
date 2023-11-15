package com.example.carinsurance.repositories;

import com.example.carinsurance.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
