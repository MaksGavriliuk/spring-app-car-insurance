package com.example.carinsurance.repositories;

import com.example.carinsurance.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findByBrand(String brand);
}
