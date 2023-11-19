package com.example.carinsurance.config;

import com.example.carinsurance.auth.SearchEngineUserRoleByUserAuthentication;
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

    private final UserAuthenticationRepository userAuthenticationRepository;
    private final SearchEngineUserRoleByUserAuthentication searchEngineUserRoleByUserAuthentication;

    @Bean
    public UserDetailsService userDetailsService() {
        return login ->
            searchEngineUserRoleByUserAuthentication.searchUser(userAuthenticationRepository.findByLogin(login));
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
