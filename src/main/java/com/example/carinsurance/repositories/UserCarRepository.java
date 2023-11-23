package com.example.carinsurance.repositories;

import com.example.carinsurance.models.UserCar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCarRepository extends JpaRepository<UserCar, Integer> {
    Optional<UserCar> findCarsByUserId(int userId);
}
