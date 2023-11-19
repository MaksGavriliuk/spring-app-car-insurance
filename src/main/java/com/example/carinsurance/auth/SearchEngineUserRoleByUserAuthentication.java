package com.example.carinsurance.auth;

import com.example.carinsurance.exceptions.UserAuthenticationException;
import com.example.carinsurance.models.UserAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SearchEngineUserRoleByUserAuthentication {

    private final Map<String, SearchEngineUserByUserAuthentication> map;

    public UserDetails searchUser(UserAuthentication userAuthentication) {
        for (SearchEngineUserByUserAuthentication searchEngine : map.values()) {
            Optional<? extends UserDetails> userDetailsOptional = searchEngine.findUserByUserAuthentication(userAuthentication);
            if (userDetailsOptional.isPresent()) {
                return userDetailsOptional.get();
            }
        }
        throw new UserAuthenticationException("Пользователь не найден");
    }

}
