package com.example.carinsurance.controllers;

import com.example.carinsurance.dtos.ContractDTO;
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

import java.util.List;


@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;


//    @GetMapping
//    public List<Contract> getContracts() {
//        return contractService.listContracts();
//    }

    @GetMapping
    public ResponseEntity<List<Contract>> getContractsByUserId(@RequestParam(name = "user-id", required = false) Integer userId) {
        List<Contract> contracts;
        if (userId != null) {
            contracts = contractService.getContractsByUserId(userId);
        } else {
            contracts = contractService.listContracts();
        }
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

}
