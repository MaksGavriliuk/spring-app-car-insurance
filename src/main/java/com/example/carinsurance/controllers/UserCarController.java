package com.example.carinsurance.controllers;

import com.example.carinsurance.dtos.UserCarDTO;
import com.example.carinsurance.models.Car;
import com.example.carinsurance.models.UserCar;
import com.example.carinsurance.services.UserCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/user-cars")
@RequiredArgsConstructor
public class UserCarController {

    private final UserCarService userCarService;


    @GetMapping
    public List<UserCar> getUserCars(
            @RequestParam(name = "user-id", required = false) Integer userId,
            @RequestParam(name = "car-id", required = false) Integer carId
    ) {
        return userCarService.listUserCars(userId, carId);
    }

    @GetMapping("/cars/{id}")
    public List<Car> getCarsByUserId(@PathVariable int id) {
        return userCarService.getCarsByUserId(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createUserCar(@RequestBody UserCarDTO userCarDTO) {
        userCarService.saveUserCar(userCarDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserCar(@PathVariable Integer id) {
        userCarService.deleteUserCar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserCar(@PathVariable Integer id, @RequestBody UserCarDTO userCarDTO) {
        userCarService.updateUserCar(id, userCarDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public UserCar getUserCarById(@PathVariable Integer id) {
        return userCarService.getUserCarById(id);
    }


}
