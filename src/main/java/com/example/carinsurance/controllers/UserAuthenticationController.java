package com.example.carinsurance.controllers;

import com.example.carinsurance.models.UserAuthentication;
import com.example.carinsurance.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auths")
public class UserAuthenticationController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;


    @GetMapping
    public List<UserAuthentication> listUserAuthentication() {
        return userAuthenticationService.listUserAuthentication();
    }

    @PostMapping("/create")
    public ResponseEntity<UserAuthentication> createUserAuthentication(
            @RequestBody UserAuthentication userAuthentication) {
        UserAuthentication savedUserAuthentication = userAuthenticationService.saveUserAuthentication(userAuthentication);
        return ResponseEntity.ok(savedUserAuthentication);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAuthentication> getUserAuthenticationById(@PathVariable Integer id) {
        UserAuthentication userAuthentication = userAuthenticationService.getUserAuthenticationById(id);
        return userAuthentication != null ? ResponseEntity.ok(userAuthentication) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAuthentication(@PathVariable Integer id) {
        userAuthenticationService.deleteUserAuthentication(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/validate-password/{id}")
    public ResponseEntity<Boolean> validatePassword(@PathVariable Integer id, @RequestBody Map<String, String> requestBody) {
        boolean isValid = userAuthenticationService.isPasswordValid(id, requestBody.get("password"));
        return ResponseEntity.ok(isValid);
    }
}
