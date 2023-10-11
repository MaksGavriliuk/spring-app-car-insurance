package com.example.carinsurance.repositories;

import com.example.carinsurance.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.ui.Model;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Integer> {
//    List<Model> findByBrandName(String brandName);
}
