package com.example.carinsurance.services;

import com.example.carinsurance.models.InsuranceType;
import com.example.carinsurance.repositories.InsuranceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceTypeService {

    @Autowired
    private InsuranceTypeRepository insuranceTypeRepository;

    public List<InsuranceType> listInsuranceTypes(){
        return insuranceTypeRepository.findAll();
    }

    public void saveInsuranceType(InsuranceType insuranceType) {
        insuranceTypeRepository.save(insuranceType);
    }

    public InsuranceType getInsuranceTypeById(int id) {
        return insuranceTypeRepository.findById(id).orElse(null);
    }

    public void deleteInsuranceType(int id) {
        insuranceTypeRepository.deleteById(id);
    }

}
