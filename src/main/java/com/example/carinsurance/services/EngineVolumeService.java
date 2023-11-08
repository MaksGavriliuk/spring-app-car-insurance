package com.example.carinsurance.services;

import com.example.carinsurance.exceptions.EngineVolumeException;
import com.example.carinsurance.models.EngineVolume;
import com.example.carinsurance.repositories.EngineVolumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EngineVolumeService {

    private final EngineVolumeRepository engineVolumeRepository;


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

    public void updateEngineVolume(int id, EngineVolume engineVolume) {
        engineVolume.setId(id);
        engineVolumeRepository.save(engineVolume);
    }

    public EngineVolume getEngineVolumeById(int id) {
        return engineVolumeRepository.findById(id)
                .orElseThrow(() -> new EngineVolumeException("Объём двигателя с таким id не найден"));
    }

}
