package com.example.carinsurance.controllers;

import com.example.carinsurance.models.InsuranceType;
import com.example.carinsurance.services.InsuranceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/ins-types")
@RequiredArgsConstructor
public class InsuranceTypeController {

    private final InsuranceTypeService insuranceTypeService;


    @GetMapping
    public List<InsuranceType> getInsuranceTypes() {
        return insuranceTypeService.listInsuranceTypes();
    }

    @PostMapping("/create")
    public ResponseEntity<InsuranceType> createInsuranceType(@RequestBody InsuranceType insuranceType) {
        insuranceTypeService.saveInsuranceType(insuranceType);
        return ResponseEntity.status(HttpStatus.CREATED).body(insuranceType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsuranceType(@PathVariable Integer id) {
        insuranceTypeService.deleteInsuranceType(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateInsuranceType(@PathVariable Integer id, @RequestBody InsuranceType insuranceType) {
        insuranceTypeService.updateInsuranceType(id, insuranceType);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public InsuranceType getInsuranceTypeById(@PathVariable Integer id) {
        return insuranceTypeService.getInsuranceTypeById(id);
    }

}
