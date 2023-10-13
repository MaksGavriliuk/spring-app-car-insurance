package com.example.carinsurance.controllers;

import com.example.carinsurance.models.FuelType;
import com.example.carinsurance.services.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/fuel-types")
public class FuelTypeController {

    @Autowired
    private FuelTypeService fuelTypeService;


    @GetMapping
    public List<FuelType> getFuelTypes(@RequestParam(name = "fuel-type", required = false) String fuelType) {
        return fuelTypeService.listFuelTypes(fuelType);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createFuelType(@RequestBody FuelType fuelType) {
        fuelTypeService.saveFuelType(fuelType);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuelType(@PathVariable Integer id) {
        fuelTypeService.deleteFuelType(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public FuelType getFuelTypeById(@PathVariable Integer id) {
        return fuelTypeService.getFuelTypeById(id);
    }

}