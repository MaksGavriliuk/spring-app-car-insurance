package com.example.carinsurance.auth;


import com.example.carinsurance.config.JwtService;
import com.example.carinsurance.exceptions.UserAuthenticationException;
import com.example.carinsurance.models.UserAuthentication;
import com.example.carinsurance.repositories.UserAuthenticationRepository;
import com.example.carinsurance.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.carinsurance.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final SearchEngineUserRoleByUserAuthentication searchEngineUserRoleByUserAuthentication;
    private final UserRepository userRepository;
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

        UserDetails user = searchEngineUserRoleByUserAuthentication.searchUser(userAuthenticationRepository.findByLogin(request.getLogin()));
        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(user)
                .build();

    }

}
