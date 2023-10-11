package com.example.carinsurance.controllers;

import com.example.carinsurance.models.Brand;
import com.example.carinsurance.models.EngineVolume;
import com.example.carinsurance.models.Model;
import com.example.carinsurance.services.EngineVolumeService;
import com.example.carinsurance.services.ModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/engine-volumes")
public class EngineVolumeController {

    @Autowired
    private EngineVolumeService engineVolumeService;

    @GetMapping
    public List<EngineVolume> getEngineVolumes(@RequestParam(name = "engine-volume", required = false) BigDecimal engineVolume) {
        return engineVolumeService.listEngineVolumes(engineVolume);
    }

    @GetMapping("/{id}")
    public EngineVolume getEngineVolumeInfo(@PathVariable Integer id) {
        return engineVolumeService.getEngineVolumeById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createEngineVolume(@RequestBody EngineVolume engineVolume) {
        engineVolumeService.saveEngineVolume(engineVolume);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEngineVolume(@PathVariable Integer id) {
        engineVolumeService.deleteEngineVolume(id);
        return ResponseEntity.ok().build();
    }

}
