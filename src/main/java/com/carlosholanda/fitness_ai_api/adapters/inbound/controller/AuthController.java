package com.carlosholanda.fitness_ai_api.adapters.inbound.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.auth.CreateUserRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.auth.LoginRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.auth.LoginResponse;
import com.carlosholanda.fitness_ai_api.application.usecases.AuthUseCases;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthUseCases authUseCases;

    public AuthController(AuthUseCases authUseCases) {
      this.authUseCases =  authUseCases;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authUseCases.login(request);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@Valid @RequestBody CreateUserRequest request) {
        LoginResponse response = authUseCases.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
}
