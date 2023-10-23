package com.example.carinsurance.services;

import com.example.carinsurance.dtos.UserDTO;
import com.example.carinsurance.exceptions.AdminException;
import com.example.carinsurance.exceptions.UserAuthenticationException;
import com.example.carinsurance.exceptions.UserException;
import com.example.carinsurance.models.Admin;
import com.example.carinsurance.models.User;
import com.example.carinsurance.models.UserAuthentication;
import com.example.carinsurance.repositories.UserAuthenticationRepository;
import com.example.carinsurance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;


    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void saveUser(UserDTO userDTO) {
        if (userAuthenticationRepository.findByLogin(userDTO.getLogin()) != null)
            throw new UserAuthenticationException("Пользователь с таким логином уже существует");
        User user = mapUserDTOToUser(userDTO);
        userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public void updateUser(int id, UserDTO userDTO) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException("Пользователя с таким id не существует"));

        UserAuthentication userAuthentication = user.getUserAuthentication();

        String login = userDTO.getLogin();
        String password = userDTO.getPassword();

        if (!userAuthentication.getLogin().equals(login)) {
            if (userAuthenticationRepository.findByLogin(login) != null)
                throw new UserAuthenticationException("Пользователь с таким логином уже существует");
            userAuthentication.setLogin(login);
        }
        if (!userAuthentication.getPassword().equals(password))
            userAuthentication.setPassword(password);

        userAuthenticationRepository.save(userAuthentication);
        user.setUserAuthentication(userAuthentication);

        user.setSurname(userDTO.getSurname());
        user.setName(userDTO.getName());
        user.setPatronymic(userDTO.getPatronymic());

        userRepository.save(user);

    }

    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserAuthenticationException("Пользователя с таким логином не существует"));
    }

    public User mapUserDTOToUser(UserDTO userDTO) {

        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.setLogin(userDTO.getLogin());
        userAuthentication.setPassword(userDTO.getPassword());
        userAuthenticationRepository.save(userAuthentication);

        User user = new User();
        user.setUserAuthentication(userAuthentication);
        user.setSurname(userDTO.getSurname());
        user.setName(userDTO.getName());
        user.setPatronymic(userDTO.getPatronymic());
        user.setSex(userDTO.getSex());
        user.setAge(userDTO.getAge());
        user.setExperience(userDTO.getExperience());
        userRepository.save(user);

        return user;

    }

}
