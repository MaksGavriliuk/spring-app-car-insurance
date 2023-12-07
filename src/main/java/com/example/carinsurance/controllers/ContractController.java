package com.example.carinsurance.controllers;

import com.example.carinsurance.dtos.ContractDTO;
import com.example.carinsurance.filter.ContractFilter;
import com.example.carinsurance.models.Contract;
import com.example.carinsurance.services.ContractService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;


    @GetMapping
    public ResponseEntity<List<Contract>> getContractsByUserId(@RequestParam(name = "user-id", required = false) Integer userId) {
        List<Contract> contracts = contractService.listContracts(userId);
        return ResponseEntity.ok().body(contracts);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createContract(@RequestBody ContractDTO contractDTO) {
        contractService.saveContract(contractDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable int id) {
        contractService.deleteContract(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateContract(@PathVariable Integer id, @RequestBody ContractDTO contractDTO) {
        contractService.updateContract(id, contractDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Contract getContractById(@PathVariable int id) {
        return contractService.getContractById(id);
    }

    @GetMapping("/not-approved")
    public ResponseEntity<List<Contract>> getNotApprovedContracts() {
        List<Contract> contracts = contractService.findNotApprovedContracts();
        return ResponseEntity.ok().body(contracts);
    }

    @GetMapping("/approved/{id}")
    public ResponseEntity<List<Contract>> getApprovedContracts(@PathVariable int id) {
        List<Contract> contracts = contractService.findApprovedContracts(id);
        return ResponseEntity.ok().body(contracts);
    }

    @PostMapping("/statistic")
    public ResponseEntity<List<Contract>> getContractsStatistic(@RequestBody ContractFilter contractFilter) {
        List<Contract> contracts = contractService.statistics(contractFilter);
        return ResponseEntity.ok().body(contracts);
    }


}
