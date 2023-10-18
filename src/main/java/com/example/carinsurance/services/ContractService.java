package com.example.carinsurance.services;

import com.example.carinsurance.dtos.ContractDTO;
import com.example.carinsurance.exceptions.ContractException;
import com.example.carinsurance.exceptions.InsuranceAgentException;
import com.example.carinsurance.exceptions.InsuranceTypeException;
import com.example.carinsurance.exceptions.UserCarException;
import com.example.carinsurance.models.Contract;
import com.example.carinsurance.repositories.ContractRepository;
import com.example.carinsurance.repositories.InsuranceAgentRepository;
import com.example.carinsurance.repositories.InsuranceTypeRepository;
import com.example.carinsurance.repositories.UserCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private InsuranceAgentRepository insuranceAgentRepository;
    @Autowired
    private InsuranceTypeRepository insuranceTypeRepository;
    @Autowired
    private UserCarRepository userCarRepository;


    public List<Contract> listContracts() {
        return contractRepository.findAll();
    }

    public void saveContract(ContractDTO contractDTO) {
        insuranceAgentRepository.findById(contractDTO.getInsuranceAgentId())
                .orElseThrow(() -> new InsuranceAgentException("Страхового агента с таким id не существует"));
        insuranceTypeRepository.findById(contractDTO.getInsuranceTypeId())
                .orElseThrow(() -> new InsuranceTypeException("Типа страховки с таким id не существует"));
        userCarRepository.findById(contractDTO.getUserCarId())
                .orElseThrow(() -> new UserCarException("Связи машина-пользователь с таким id не существует"));
        Contract contract = mapContractDTOToContract(contractDTO);
        contractRepository.save(contract);
    }

    public void deleteContract(int id) {
        contractRepository.deleteById(id);
    }

    public Contract getContractById(int id) {
        return contractRepository.findById(id).orElseThrow(() -> new ContractException("Страховки с таким id не существует"));
    }


    public Contract mapContractDTOToContract(ContractDTO contractDTO) {
        Contract contract = new Contract();
        contract.setInsuranceAgent(insuranceAgentRepository.findById(contractDTO.getInsuranceAgentId()).orElse(null));
        contract.setInsuranceType(insuranceTypeRepository.findById(contractDTO.getInsuranceTypeId()).orElse(null));
        contract.setUserCar(userCarRepository.findById(contractDTO.getUserCarId()).orElse(null));
        contract.setStartDate(contractDTO.getStartDate());
        contract.setEndDate(contractDTO.getEndDate());
        contract.setAmount(contractDTO.getAmount());
        contract.setPayoutAmount(contractDTO.getPayoutAmount());
        contract.setStatus(contractDTO.getStatus());
        return contract;
    }

}

