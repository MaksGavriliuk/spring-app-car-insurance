package com.example.carinsurance.services;

import com.example.carinsurance.dtos.CarDTO;
import com.example.carinsurance.exceptions.CarException;
import com.example.carinsurance.exceptions.EngineVolumeException;
import com.example.carinsurance.exceptions.FuelTypeException;
import com.example.carinsurance.exceptions.ModelException;
import com.example.carinsurance.repositories.CarRepository;
import com.example.carinsurance.models.Car;
import com.example.carinsurance.repositories.EngineVolumeRepository;
import com.example.carinsurance.repositories.FuelTypeRepository;
import com.example.carinsurance.repositories.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CarService {

    private final ModelRepository modelRepository;
    private final EngineVolumeRepository engineVolumeRepository;
    private final FuelTypeRepository fuelTypeRepository;
    private final CarRepository carRepository;


    public List<Car> listCars() {
        return carRepository.findAll();
    }

    public Car saveCar(CarDTO carDTO) {

        modelRepository.findById(carDTO.getModelId()).orElseThrow(() -> new ModelException("Модель не найдена"));
        engineVolumeRepository.findById(carDTO.getEngineVolumeId()).orElseThrow(() -> new EngineVolumeException("Объём двигателя не найден"));
        fuelTypeRepository.findById(carDTO.getFuelTypeId()).orElseThrow(() -> new FuelTypeException("Тип топлива не найден"));

        Car car = mapCarDTOToCar(carDTO);
        carRepository.save(car);

        return car;

    }

    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }

    public void updateCar(int id, CarDTO carDTO) {

        modelRepository.findById(carDTO.getModelId()).orElseThrow(() -> new ModelException("Модель не найдена"));
        engineVolumeRepository.findById(carDTO.getEngineVolumeId()).orElseThrow(() -> new EngineVolumeException("Объём двигателя не найден"));
        fuelTypeRepository.findById(carDTO.getFuelTypeId()).orElseThrow(() -> new FuelTypeException("Тип топлива не найден"));

        Car car = mapCarDTOToCar(carDTO);
        car.setId(id);
        carRepository.save(car);

    }

    public Car getCarById(int id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarException("Машина с таким id не найдена"));
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
