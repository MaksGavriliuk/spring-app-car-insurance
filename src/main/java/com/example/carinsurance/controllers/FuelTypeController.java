package com.example.carinsurance.controllers;

import com.example.carinsurance.models.FuelType;
import com.example.carinsurance.services.FuelTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/fuel-types")
@RequiredArgsConstructor
public class FuelTypeController {

    private final FuelTypeService fuelTypeService;


    @GetMapping
    public List<FuelType> getFuelTypes(@RequestParam(name = "fuel-type", required = false) String fuelType) {
        return fuelTypeService.listFuelTypes(fuelType);
    }

    @PostMapping("/create")
    public ResponseEntity<FuelType> createFuelType(@RequestBody FuelType fuelType) {
        fuelTypeService.saveFuelType(fuelType);
        return ResponseEntity.status(HttpStatus.CREATED).body(fuelType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuelType(@PathVariable Integer id) {
        fuelTypeService.deleteFuelType(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFuelType(@PathVariable Integer id, @RequestBody FuelType fuelType) {
        fuelTypeService.updateFuelType(id, fuelType);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public FuelType getFuelTypeById(@PathVariable Integer id) {
        return fuelTypeService.getFuelTypeById(id);
    }

}