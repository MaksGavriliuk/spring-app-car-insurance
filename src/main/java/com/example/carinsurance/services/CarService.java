package com.example.carinsurance.services;

import com.example.carinsurance.dtos.CarDTO;
import com.example.carinsurance.exceptions.EngineVolumeNotFoundException;
import com.example.carinsurance.exceptions.FuelTypeNotFoundException;
import com.example.carinsurance.exceptions.ModelNotFoundException;
import com.example.carinsurance.repositories.CarRepository;
import com.example.carinsurance.models.Car;
import com.example.carinsurance.repositories.EngineVolumeRepository;
import com.example.carinsurance.repositories.FuelTypeRepository;
import com.example.carinsurance.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarService {

    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private EngineVolumeRepository engineVolumeRepository;
    @Autowired
    private FuelTypeRepository fuelTypeRepository;
    @Autowired
    private CarRepository carRepository;


    public List<Car> listCars() {
        return carRepository.findAll();
    }

    public void saveCar(CarDTO carDTO) {

        modelRepository.findById(carDTO.getModelId()).orElseThrow(() -> new ModelNotFoundException("Модель не найдена"));
        engineVolumeRepository.findById(carDTO.getEngineVolumeId()).orElseThrow(() -> new EngineVolumeNotFoundException("Объём двигателя не найден"));
        fuelTypeRepository.findById(carDTO.getFuelTypeId()).orElseThrow(() -> new FuelTypeNotFoundException("Тип топлива не найден"));

        Car car = mapCarDTOToCar(carDTO);
        carRepository.save(car);

    }

    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }

    public Car getCarById(int id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car mapCarDTOToCar(CarDTO carDTO) {
        Car car = new Car();
        car.setModel(modelRepository.findById(carDTO.getModelId()).orElseThrow());
        car.setEngineVolume(engineVolumeRepository.findById(carDTO.getEngineVolumeId()).orElseThrow());
        car.setFuelType(fuelTypeRepository.findById(carDTO.getFuelTypeId()).orElseThrow());
        car.setProductionYear(carDTO.getProductionYear());
        car.setCurrentValue(carDTO.getCurrentValue());
        return car;
    }

}
