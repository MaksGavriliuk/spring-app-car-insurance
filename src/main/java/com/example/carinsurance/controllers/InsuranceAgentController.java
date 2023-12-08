package com.example.carinsurance.controllers;


import com.example.carinsurance.calculate.PremiumCalculation;
import com.example.carinsurance.dtos.InsuranceAgentDTO;
import com.example.carinsurance.models.Contract;
import com.example.carinsurance.models.InsuranceAgent;
import com.example.carinsurance.services.InsuranceAgentService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/agents")
@RequiredArgsConstructor
public class InsuranceAgentController {

    private final InsuranceAgentService insuranceAgentService;


    @GetMapping
    public List<InsuranceAgent> getInsuranceAgents() {
        return insuranceAgentService.listInsuranceAgents();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createInsuranceAgent(@RequestBody InsuranceAgentDTO insuranceAgentDTO) {
        insuranceAgentService.saveInsuranceAgent(insuranceAgentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsuranceAgent(@PathVariable Integer id) {
        insuranceAgentService.deleteInsuranceAgent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceAgent> getInsuranceAgentById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(insuranceAgentService.getInsuranceAgentById(id));
    }

    @PostMapping("/premium")
    public ResponseEntity<BigDecimal> calculatePremium(
            @RequestBody PremiumCalculation premiumCalculation)
    {
        BigDecimal premium = insuranceAgentService.calculatePremium(premiumCalculation);
        return ResponseEntity.ok().body(premium);
    }

}
