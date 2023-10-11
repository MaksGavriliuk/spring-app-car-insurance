package com.example.carinsurance.services;

import com.example.carinsurance.models.UserAuthentication;
import com.example.carinsurance.repositories.UserAuthenticationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserAuthenticationService {

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;


    public List<UserAuthentication> listUserAuthentication() {
        return userAuthenticationRepository.findAll();
    }

    public UserAuthentication saveUserAuthentication(UserAuthentication userAuthentication) {
        userAuthentication.setPassword(new BCryptPasswordEncoder().encode(userAuthentication.getPassword()));
        return userAuthenticationRepository.save(userAuthentication);
    }

    public UserAuthentication getUserAuthenticationById(Integer id) {
        return userAuthenticationRepository.findById(id).orElse(null);
    }

    public UserAuthentication getUserAuthenticationByLogin(String login) {
        return userAuthenticationRepository.findByLogin(login);
    }

    public void deleteUserAuthentication(Integer id) {
        userAuthenticationRepository.deleteById(id);
    }

    public boolean isPasswordValid(Integer id, String password) {
        UserAuthentication userAuthentication = userAuthenticationRepository.findById(id).orElse(null);
        if (userAuthentication != null) {
//            String hash = new BCryptPasswordEncoder().encode(password);
//            log.info("PASSWORD1: {}", hash);
//            log.info("PASSWORD2: {}", userAuthentication.getPassword());
            return new BCryptPasswordEncoder().matches(password, userAuthentication.getPassword());
        }
        return false;
    }
}
