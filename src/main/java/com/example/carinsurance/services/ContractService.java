package com.example.carinsurance.services;

import com.example.carinsurance.dtos.ContractDTO;
import com.example.carinsurance.exceptions.ContractException;
import com.example.carinsurance.exceptions.InsuranceAgentException;
import com.example.carinsurance.exceptions.InsuranceTypeException;
import com.example.carinsurance.exceptions.UserCarException;
import com.example.carinsurance.filter.ContractFilter;
import com.example.carinsurance.models.Contract;
import com.example.carinsurance.repositories.ContractRepository;
import com.example.carinsurance.repositories.InsuranceAgentRepository;
import com.example.carinsurance.repositories.InsuranceTypeRepository;
import com.example.carinsurance.repositories.UserCarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final InsuranceAgentRepository insuranceAgentRepository;
    private final InsuranceTypeRepository insuranceTypeRepository;
    private final UserCarRepository userCarRepository;


    public List<Contract> getContractsByUserId(int userId) {
        return contractRepository
                .findAll()
                .stream()
                .filter(contract -> contract.getUserCar().getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    public List<Contract> listContracts(Integer userId) {
        List<Contract> contracts = contractRepository.findAll();
        return (userId == null) ? contracts : contracts
                .stream()
                .filter(contract -> Objects.equals(contract.getUserCar().getUser().getId(), userId))
                .collect(Collectors.toList());
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

    public Contract updateContract(int id, ContractDTO contractDTO) {

        insuranceAgentRepository.findById(contractDTO.getInsuranceAgentId())
                .orElseThrow(() -> new InsuranceAgentException("Страхового агента с таким id не существует"));
        insuranceTypeRepository.findById(contractDTO.getInsuranceTypeId())
                .orElseThrow(() -> new InsuranceTypeException("Типа страховки с таким id не существует"));
        userCarRepository.findById(contractDTO.getUserCarId())
                .orElseThrow(() -> new UserCarException("Связи машина-пользователь с таким id не существует"));

        Contract contract = mapContractDTOToContract(contractDTO);
        contract.setId(id);
        contractRepository.save(contract);

        return contract;

    }

    public Contract getContractById(int id) {
        return contractRepository.findById(id).orElseThrow(() -> new ContractException("Страховки с таким id не существует"));
    }

    public List<Contract> findNotApprovedContracts() {
        return contractRepository
                .findAll()
                .stream()
                .filter(contract -> contract.getStatus().equalsIgnoreCase("не одобрена"))
                .collect(Collectors.toList());
    }

    public List<Contract> findApprovedContracts(int agentId) {
        return contractRepository
                .findAll()
                .stream()
                .filter(contract -> contract.getInsuranceAgent().getId() == agentId)
                .collect(Collectors.toList());
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

    public List<Contract> statistics(ContractFilter contractFilter) {
        return contractRepository.findAll()
                .stream()
                .filter(contract -> contractFilter.getBrandId() == null || contract.getUserCar().getCar().getModel().getBrand().getId().equals(contractFilter.getBrandId()))
                .filter(contract -> contractFilter.getEngineVolumeId() == null || contract.getUserCar().getCar().getEngineVolume().getId().equals(contractFilter.getEngineVolumeId()))
                .filter(contract -> contractFilter.getFuelTypeId() == null || contract.getUserCar().getCar().getFuelType().getId().equals(contractFilter.getFuelTypeId()))
                .filter(contract -> contractFilter.getUserId() == null || contract.getUserCar().getUser().getId().equals(contractFilter.getUserId()))
                .filter(contract -> contractFilter.getInsuranceAgentId() == null || contract.getInsuranceAgent().getId().equals(contractFilter.getInsuranceAgentId()))
                .filter(contract -> contractFilter.getInsuranceTypeId() == null || contract.getInsuranceType().getId().equals(contractFilter.getInsuranceTypeId()))
                .filter(contract -> contractFilter.getStartDate() == null || contract.getStartDate().after(contractFilter.getStartDate()))
                .filter(contract -> contractFilter.getEndDate() == null || contract.getEndDate().before(contractFilter.getEndDate()))
                .filter(contract -> contractFilter.getAmount() == null || contract.getAmount().compareTo(contractFilter.getAmount()) > 0)
                .filter(contract -> contractFilter.getPayoutAmount() == null || contract.getPayoutAmount().compareTo(contractFilter.getPayoutAmount()) > 0)
                .filter(contract -> contractFilter.getStatus() == null || contract.getStatus().equals(contractFilter.getStatus()))
                .collect(Collectors.toList());
    }

}

