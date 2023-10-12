package com.example.carinsurance.services;

import com.example.carinsurance.models.EngineVolume;
import com.example.carinsurance.models.FuelType;
import com.example.carinsurance.models.Model;
import com.example.carinsurance.repositories.CarRepository;
import com.example.carinsurance.models.Car;
import com.example.carinsurance.repositories.EngineVolumeRepository;
import com.example.carinsurance.repositories.FuelTypeRepository;
import com.example.carinsurance.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(int id) {
        return carRepository.findById(id).orElse(null);
    }

//    public Car createCar(Car car, String modelName, String engineVolume, String fuelType) {
//        return carRepository.save(car);
//    }

    public Car createCar(Car car, String modelName, String engineVolume, String fuelType) {
        // Создание модели
        Model model = new Model();
        model.setModel(modelName);
        modelRepository.save(model);

        BigDecimal engineVolumeValue = new BigDecimal(engineVolume);
        // Установка объема двигателя
        EngineVolume engineVolumeEntity = new EngineVolume();
        engineVolumeEntity.setEngineVolume(engineVolumeValue);
        engineVolumeRepository.save(engineVolumeEntity);

        // Создание типа топлива
        FuelType fuelTypeEntity = new FuelType();
        fuelTypeEntity.setFuelType(fuelType);
        fuelTypeRepository.save(fuelTypeEntity);

        // Установка связей в машине
        car.setModel(model);
        car.setEngineVolume(engineVolumeEntity);
        car.setFuelType(fuelTypeEntity);

        return carRepository.save(car);
    }


    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }

}
