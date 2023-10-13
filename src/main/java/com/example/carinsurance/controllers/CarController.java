package com.example.carinsurance.controllers;

import com.example.carinsurance.models.Car;
import com.example.carinsurance.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;


    @GetMapping
    public List<Car> getCars() {
        return carService.listCars();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createCar(
            @RequestBody Car car,
            @RequestParam(value = "brand", required = true) String brandName,
            @RequestParam(value = "model", required = true) String modelName,
            @RequestParam(value = "engine-volume", required = true) String engineVolume,
            @RequestParam(value = "fuel-type", required = true) String fuelType
    ) {
        carService.saveCar(car, brandName, modelName, engineVolume, fuelType);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable int id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable int id) {
        return carService.getCarById(id);
    }

}
