package com.example.carinsurance.services;

import com.example.carinsurance.models.Brand;
import com.example.carinsurance.models.EngineVolume;
import com.example.carinsurance.models.FuelType;
import com.example.carinsurance.models.Model;
import com.example.carinsurance.repositories.BrandRepository;
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
    private BrandRepository brandRepository;
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

    public void saveCar(Car car, String brandName, String modelName, String engineVolumeValue, String fuelTypeValue) {

        List<Brand> brands = brandRepository.findByBrand(brandName);
        Brand brand;

        if (brands.isEmpty()) {
            brand = new Brand();
            brand.setBrand(brandName);
            brandRepository.save(brand);
        } else {
            brand = brands.get(0);
        }

        List<Model> models = modelRepository.findByModel(modelName);
        Model model;

        if (models.isEmpty()) {
            model = new Model();
            model.setModel(modelName);
            model.setBrand(brand);
            modelRepository.save(model);
        } else {
            model = models.get(0);
        }

        List<EngineVolume> engineVolumes = engineVolumeRepository.findByEngineVolume(new BigDecimal(engineVolumeValue));
        EngineVolume engineVolume;

        if (engineVolumes.isEmpty()) {
            engineVolume = new EngineVolume();
            engineVolume.setEngineVolume(new BigDecimal(engineVolumeValue));
            engineVolumeRepository.save(engineVolume);
        } else {
            engineVolume = engineVolumes.get(0);
        }

        List<FuelType> fuelTypes = fuelTypeRepository.findByFuelType(fuelTypeValue);
        FuelType fuelType;

        if (fuelTypes.isEmpty()) {
            fuelType = new FuelType();
            fuelType.setFuelType(fuelTypeValue);
            fuelTypeRepository.save(fuelType);
        } else {
            fuelType = fuelTypes.get(0);
        }

        car.setModel(model);
        car.setEngineVolume(engineVolume);
        car.setFuelType(fuelType);

        carRepository.save(car);

    }

    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }

    public Car getCarById(int id) {
        return carRepository.findById(id).orElse(null);
    }

}
