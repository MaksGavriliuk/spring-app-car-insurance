package com.example.carinsurance.controllers;

import com.example.carinsurance.models.Brand;
import com.example.carinsurance.models.InsuranceType;
import com.example.carinsurance.services.InsuranceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ins-types")
public class InsuranceTypeController {

    @Autowired
    private InsuranceTypeService insuranceTypeService;

    @GetMapping
    public List<InsuranceType> getInsuranceTypes(){
        return insuranceTypeService.listInsuranceTypes();
    }

    @GetMapping("/{id}")
    public InsuranceType getInsuranceTypeInfo(@PathVariable Integer id) {
        return insuranceTypeService.getInsuranceTypeById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createInsuranceType(@RequestBody InsuranceType insuranceType) {
        insuranceTypeService.saveInsuranceType(insuranceType);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsuranceType(@PathVariable Integer id) {
        insuranceTypeService.deleteInsuranceType(id);
        return ResponseEntity.ok().build();
    }

}
