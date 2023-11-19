package com.example.carinsurance.config;

import com.example.carinsurance.exceptions.UserException;
import com.example.carinsurance.models.Admin;
import com.example.carinsurance.models.InsuranceAgent;
import com.example.carinsurance.models.User;
import com.example.carinsurance.repositories.AdminRepository;
import com.example.carinsurance.repositories.InsuranceAgentRepository;
import com.example.carinsurance.repositories.UserAuthenticationRepository;
import com.example.carinsurance.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor

public class ApplicationConfig {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final InsuranceAgentRepository insuranceAgentRepository;
    private final UserAuthenticationRepository userAuthenticationRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return login -> {
            Optional<User> user = userRepository.findByUserAuthentication(userAuthenticationRepository.findByLogin(login));
            if (user.isPresent()) {
                return user.get(); // Return the User object if found
            } else {
                Optional<Admin> admin = adminRepository.findByUserAuthentication(userAuthenticationRepository.findByLogin(login));
                if (admin.isPresent()) {
                    return admin.get(); // Return the Admin object if found
                } else {
                    Optional<InsuranceAgent> insuranceAgent = insuranceAgentRepository.findByUserAuthentication(userAuthenticationRepository.findByLogin(login));
                    if (insuranceAgent.isPresent())
                        return insuranceAgent.get();
                    else throw new UsernameNotFoundException("Пользователь не найден");
                }
            }
        };
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
