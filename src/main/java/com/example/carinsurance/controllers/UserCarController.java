package com.example.carinsurance.controllers;

import com.example.carinsurance.dtos.UserCarDTO;
import com.example.carinsurance.models.UserCar;
import com.example.carinsurance.services.UserCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/user-cars")
public class UserCarController {

    @Autowired
    private UserCarService userCarService;


    @GetMapping
    public List<UserCar> getUserCars(){
        return userCarService.listUserCars();
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

    @GetMapping("/{id}")
    public UserCar getUserCarById(@PathVariable Integer id) {
        return userCarService.getUserCarById(id);
    }


}
