package com.example.carinsurance.repositories;

import com.example.carinsurance.models.UserCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCarRepository extends JpaRepository<UserCar, Integer> {
}
