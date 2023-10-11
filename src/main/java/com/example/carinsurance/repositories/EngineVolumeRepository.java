package com.example.carinsurance.repositories;

import com.example.carinsurance.models.EngineVolume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface EngineVolumeRepository extends JpaRepository<EngineVolume, Integer> {
    List<EngineVolume> findByEngineVolume(BigDecimal engineVolume);
}
