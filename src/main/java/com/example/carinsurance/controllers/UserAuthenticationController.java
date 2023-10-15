package com.example.carinsurance.controllers;

import com.example.carinsurance.models.UserAuthentication;
import com.example.carinsurance.services.UserAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/auths")
@Slf4j
public class UserAuthenticationController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;


    @GetMapping
    public List<UserAuthentication> getUserAuthentications(@RequestParam(name = "login", required = false) String login) {
        if (login != null) {
            UserAuthentication userAuthentication = userAuthenticationService.getUserAuthenticationByLogin(login);
            return (userAuthentication == null) ? null : List.of(userAuthentication);
        }
        return userAuthenticationService.listUserAuthentications();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createUserAuthentication(
            @RequestBody UserAuthentication userAuthentication) {
        userAuthenticationService.saveUserAuthentication(userAuthentication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAuthentication(@PathVariable Integer id) {
        userAuthenticationService.deleteUserAuthentication(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public UserAuthentication getUserAuthenticationById(@PathVariable Integer id) {
        return userAuthenticationService.getUserAuthenticationById(id);
    }

    @PostMapping("/validate-password/{id}")
    public ResponseEntity<Boolean> validatePassword(@PathVariable Integer id, @RequestBody Map<String, String> requestBody) {
        boolean isValid = userAuthenticationService.isPasswordValid(id, requestBody.get("password"));
        return ResponseEntity.ok(isValid);
    }

}
