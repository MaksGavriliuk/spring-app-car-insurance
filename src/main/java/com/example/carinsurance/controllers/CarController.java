package com.example.carinsurance.controllers;

import com.example.carinsurance.models.Car;
import com.example.carinsurance.models.Model;
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

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;


    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable int id) {
        Car car = carService.getCarById(id);
        return ResponseEntity.ok(car);
    }


    @PostMapping("/create")
    public ResponseEntity<Car> createCar(
            @RequestBody Car car,
            @RequestParam(value = "model", required = true) String modelName,
            @RequestParam(value = "engine-volume", required = true) String engineVolume,
            @RequestParam(value = "fuel-type", required = true) String fuelType
    ) {
        Car createdCar = carService.createCar(car, modelName, engineVolume, fuelType);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<Car> updateCar(@PathVariable int id, @RequestBody Car updatedCar) {
//        Car car = carService.updateCar(id, updatedCar);
//        return ResponseEntity.ok(car);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable int id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<Car>> searchCarsByBrandAndEngineVolume(
//            @RequestParam("brand") String brand,
//            @RequestParam("engineVolume") BigDecimal engineVolume
//    ) {
//        List<Car> cars = carService.getCarsByBrandAndEngineVolume(brand, engineVolume);
//        return ResponseEntity.ok(cars);
//    }

}
