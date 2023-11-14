package com.example.carinsurance.auth;


import com.example.carinsurance.config.JwtService;
import com.example.carinsurance.models.Role;
import com.example.carinsurance.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.carinsurance.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .surname(request.getSurname())
                .name(request.getName())
                .patronymic(request.getPatronymic())
                .sex(request.getSex())
                .experience(request.getExperience())
                .userAuthentication(request.getUserAuthentication())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }
}
