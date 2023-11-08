package com.example.carinsurance.services;

import com.example.carinsurance.dtos.UserCarDTO;
import com.example.carinsurance.exceptions.CarException;
import com.example.carinsurance.exceptions.UserCarException;
import com.example.carinsurance.exceptions.UserException;
import com.example.carinsurance.models.UserCar;
import com.example.carinsurance.repositories.CarRepository;
import com.example.carinsurance.repositories.UserCarRepository;
import com.example.carinsurance.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserCarService {

    private final UserCarRepository userCarRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;


    public List<UserCar> listUserCars() {
        return userCarRepository.findAll();
    }

    public void saveUserCar(UserCarDTO userCarDTO) {

        userRepository.findById(userCarDTO.getUserId())
                .orElseThrow(() -> new UserException("Пользователя с таким id не существует"));
        carRepository.findById(userCarDTO.getCarId())
                .orElseThrow(() -> new CarException("Машины с таким id не существует"));

        UserCar userCar = mapUserCarDTOToUserCar(userCarDTO);
        userCarRepository.save(userCar);

    }

    public void deleteUserCar(int id){
        userCarRepository.deleteById(id);
    }

    public void updateUserCar(int id, UserCarDTO userCarDTO) {

        userRepository.findById(userCarDTO.getUserId())
                .orElseThrow(() -> new UserException("Пользователя с таким id не существует"));
        carRepository.findById(userCarDTO.getCarId())
                .orElseThrow(() -> new CarException("Машины с таким id не существует"));

        UserCar userCar = mapUserCarDTOToUserCar(userCarDTO);
        userCar.setId(id);
        userCarRepository.save(userCar);

    }

    public UserCar getUserCarById(int id){
        return userCarRepository.findById(id)
                .orElseThrow(() -> new UserCarException("Связи автомобиль-пользователь с таким id не существует"));
    }

    public UserCar mapUserCarDTOToUserCar(UserCarDTO userCarDTO) {
        UserCar userCar = new UserCar();
        userCar.setUser(userRepository.findById(userCarDTO.getUserId()).orElse(null));
        userCar.setCar(carRepository.findById(userCarDTO.getCarId()).orElse(null));
        return userCar;
    }

}
