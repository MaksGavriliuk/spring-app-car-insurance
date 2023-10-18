package com.example.carinsurance.controllers;

import com.example.carinsurance.dtos.ContractDTO;
import com.example.carinsurance.models.Contract;
import com.example.carinsurance.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;


    @GetMapping
    public List<Contract> getContracts() {
        return contractService.listContracts();
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

    @GetMapping("/{id}")
    public Contract getContractById(@PathVariable int id) {
        return contractService.getContractById(id);
    }

}
