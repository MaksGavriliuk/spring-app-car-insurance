package com.example.carinsurance.controllers;


import com.example.carinsurance.models.UserCar;
import com.example.carinsurance.services.UserCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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


}
