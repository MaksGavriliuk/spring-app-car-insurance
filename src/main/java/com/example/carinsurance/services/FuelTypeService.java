package com.example.carinsurance.services;

import com.example.carinsurance.models.FuelType;
import com.example.carinsurance.repositories.FuelTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelTypeService {

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    public List<FuelType> listFuelTypes(String fuelType) {
        if (fuelType != null) {
            return fuelTypeRepository.findByFuelType(fuelType);
        }
        return fuelTypeRepository.findAll();
    }

    public void saveFuelType(FuelType fuelType) {
        fuelTypeRepository.save(fuelType);
    }

    public void deleteFuelType(int id) {
        fuelTypeRepository.deleteById(id);
    }

    public FuelType getFuelTypeById(int id) {
        return fuelTypeRepository.findById(id).orElse(null);
    }
}