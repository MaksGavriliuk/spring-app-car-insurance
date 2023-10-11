package com.example.carinsurance.services;

import com.example.carinsurance.models.EngineVolume;
import com.example.carinsurance.repositories.EngineVolumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EngineVolumeService {

    @Autowired
    private EngineVolumeRepository engineVolumeRepository;


    public List<EngineVolume> listEngineVolumes(BigDecimal engineVolume) {
        if (engineVolume != null)
            return engineVolumeRepository.findByEngineVolume(engineVolume);
        return engineVolumeRepository.findAll();
    }

    public void saveEngineVolume(EngineVolume engineVolume) {
        engineVolumeRepository.save(engineVolume);
    }

    public void deleteEngineVolume(int id) {
        engineVolumeRepository.deleteById(id);
    }

    public EngineVolume getEngineVolumeById(int id) {
        return engineVolumeRepository.findById(id).orElse(null);
    }
    
}
