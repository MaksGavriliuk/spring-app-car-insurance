package com.example.carinsurance.repositories;

import com.example.carinsurance.auth.SearchEngineUserByUserAuthentication;
import com.example.carinsurance.models.User;
import com.example.carinsurance.models.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User, Integer>, SearchEngineUserByUserAuthentication<User> {

    Optional<User> findByUserAuthentication(UserAuthentication userAuthentication);


    @Override
    default Optional<User> findUserByUserAuthentication(UserAuthentication userAuthentication){
        return findByUserAuthentication(userAuthentication);
    }

}
