package com.example.carinsurance.auth;

import com.example.carinsurance.models.UserAuthentication;

import java.util.Optional;

public interface SearchEngineUserByUserAuthentication<T> {
    Optional<T> findUserByUserAuthentication(UserAuthentication userAuthentication);
}
