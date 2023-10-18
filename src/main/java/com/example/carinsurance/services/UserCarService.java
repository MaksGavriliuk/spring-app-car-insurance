package com.example.carinsurance.services;

import com.example.carinsurance.models.UserCar;
import com.example.carinsurance.repositories.UserCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCarService {

    @Autowired
    private UserCarRepository userCarRepository;


    public List<UserCar> listUserCars() {
        return userCarRepository.findAll();
    }

}
