package com.example.carinsurance.repositories;

import com.example.carinsurance.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
