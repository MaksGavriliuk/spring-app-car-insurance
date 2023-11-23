package com.example.carinsurance.repositories;

import com.example.carinsurance.models.Car;
import com.example.carinsurance.models.UserCar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCarRepository extends JpaRepository<UserCar, Integer> {
    List<UserCar> findCarsByUserId(int userId);
}
