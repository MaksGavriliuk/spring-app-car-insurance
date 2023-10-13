package com.example.carinsurance.services;

import com.example.carinsurance.models.UserAuthentication;
import com.example.carinsurance.repositories.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserAuthenticationService {

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;


    public List<UserAuthentication> listUserAuthentications() {
        return userAuthenticationRepository.findAll();
    }

    public UserAuthentication saveUserAuthentication(UserAuthentication userAuthentication) {
        userAuthentication.setPassword(new BCryptPasswordEncoder().encode(userAuthentication.getPassword()));
        return userAuthenticationRepository.save(userAuthentication);
    }

    public void deleteUserAuthentication(Integer id) {
        userAuthenticationRepository.deleteById(id);
    }

    public UserAuthentication getUserAuthenticationById(Integer id) {
        return userAuthenticationRepository.findById(id).orElse(null);
    }

    public UserAuthentication getUserAuthenticationByLogin(String login) {
        return userAuthenticationRepository.findByLogin(login);
    }

    public boolean isPasswordValid(Integer id, String password) {
        UserAuthentication userAuthentication = userAuthenticationRepository.findById(id).orElse(null);
        if (userAuthentication != null)
            return new BCryptPasswordEncoder().matches(password, userAuthentication.getPassword());
        return false;
    }

}
