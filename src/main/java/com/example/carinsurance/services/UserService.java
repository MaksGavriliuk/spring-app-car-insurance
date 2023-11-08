package com.example.carinsurance.services;

import com.example.carinsurance.dtos.UserDTO;
import com.example.carinsurance.exceptions.UserAuthenticationException;
import com.example.carinsurance.exceptions.UserException;
import com.example.carinsurance.models.User;
import com.example.carinsurance.models.UserAuthentication;
import com.example.carinsurance.repositories.UserAuthenticationRepository;
import com.example.carinsurance.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserAuthenticationRepository userAuthenticationRepository;


    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void saveUser(UserDTO userDTO) {

        if (userAuthenticationRepository.findByLogin(userDTO.getUserAuthentication().getLogin()) != null)
            throw new UserAuthenticationException("Пользователь с таким логином уже существует");

        userAuthenticationRepository.save(userDTO.getUserAuthentication());

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

        String login = userDTO.getUserAuthentication().getLogin();
        String password = userDTO.getUserAuthentication().getPassword();

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

        User user = new User();
        user.setUserAuthentication(userDTO.getUserAuthentication());
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
