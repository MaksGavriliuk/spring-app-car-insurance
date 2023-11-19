package com.example.carinsurance.auth;


import com.example.carinsurance.config.JwtService;
import com.example.carinsurance.exceptions.UserAuthenticationException;
import com.example.carinsurance.models.UserAuthentication;
import com.example.carinsurance.repositories.AdminRepository;
import com.example.carinsurance.repositories.InsuranceAgentRepository;
import com.example.carinsurance.repositories.UserAuthenticationRepository;
import com.example.carinsurance.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.carinsurance.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final InsuranceAgentRepository insuranceAgentRepository;
    private final AdminRepository adminRepository;
    private final UserAuthenticationRepository userAuthenticationRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        if (userAuthenticationRepository.findByLogin(request.getUserAuthentication().getLogin()) != null)
            throw new UserAuthenticationException("Пользователь с таким логином уже существует");

        var userAuth = new UserAuthentication(
                request.getUserAuthentication().getLogin(),
                passwordEncoder.encode(request.getUserAuthentication().getPassword())
        );

        var user = User.builder()
                .surname(request.getSurname())
                .name(request.getName())
                .patronymic(request.getPatronymic())
                .sex(request.getSex())
                .experience(request.getExperience())
                .userAuthentication(userAuth)
                .build();

        userAuthenticationRepository.save(userAuth);
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        String jwtToken;
        Map<String, Object> currentUser = new HashMap<>();

        UserDetails user = userRepository.findByUserAuthentication(
                userAuthenticationRepository.findByLogin(request.getLogin())).orElse(null);
        if (user != null) {
            jwtToken = jwtService.generateToken(user);
            currentUser.put("user", user);
        }
        else {
            var insuranceAgent = insuranceAgentRepository.findByUserAuthentication(
                    userAuthenticationRepository.findByLogin(request.getLogin())).orElse(null);
            if (insuranceAgent != null) {
                jwtToken = jwtService.generateToken(insuranceAgent);
                currentUser.put("user", insuranceAgent);
            }
            else {
                var admin = adminRepository.findByUserAuthentication(
                        userAuthenticationRepository.findByLogin(request.getLogin())).orElse(null);
                jwtToken = jwtService.generateToken(admin);
                currentUser.put("user", admin);
            }
        }

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(currentUser)
                .build();

    }

}
