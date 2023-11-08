package com.example.carinsurance.services;

import com.example.carinsurance.exceptions.InsuranceTypeException;
import com.example.carinsurance.models.InsuranceType;
import com.example.carinsurance.repositories.InsuranceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class InsuranceTypeService {

    private final InsuranceTypeRepository insuranceTypeRepository;


    public List<InsuranceType> listInsuranceTypes() {
        return insuranceTypeRepository.findAll();
    }

    public void saveInsuranceType(InsuranceType insuranceType) {
        insuranceTypeRepository.save(insuranceType);
    }

    public void deleteInsuranceType(int id) {
        insuranceTypeRepository.deleteById(id);
    }

    public void updateInsuranceType(int id, InsuranceType insuranceType) {
        insuranceType.setId(id);
        insuranceTypeRepository.save(insuranceType);
    }

    public InsuranceType getInsuranceTypeById(int id) {
        return insuranceTypeRepository.findById(id)
                .orElseThrow(() -> new InsuranceTypeException("Тип страховки с таким id не найден"));
    }

}
