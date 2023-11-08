package com.example.carinsurance.services;

import com.example.carinsurance.exceptions.FuelTypeException;
import com.example.carinsurance.models.FuelType;
import com.example.carinsurance.repositories.FuelTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FuelTypeService {

    private final FuelTypeRepository fuelTypeRepository;


    public List<FuelType> listFuelTypes(String fuelType) {
        if (fuelType != null)
            return fuelTypeRepository.findByFuelType(fuelType);
        return fuelTypeRepository.findAll();
    }

    public void saveFuelType(FuelType fuelType) {
        fuelTypeRepository.save(fuelType);
    }

    public void deleteFuelType(int id) {
        fuelTypeRepository.deleteById(id);
    }

    public void updateFuelType(int id, FuelType fuelType) {
        fuelType.setId(id);
        fuelTypeRepository.save(fuelType);
    }

    public FuelType getFuelTypeById(int id) {
        return fuelTypeRepository.findById(id)
                .orElseThrow(() -> new FuelTypeException("Тип топлива с таким id не найден"));
    }

}