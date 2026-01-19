package com.carlosholanda.fitness_ai_api.adapters.inbound.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.auth.CreateUserRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.auth.LoginRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.auth.LoginResponse;
import com.carlosholanda.fitness_ai_api.application.usecases.AuthUseCases;
import com.carlosholanda.fitness_ai_api.infrastructure.response.ApiResponse;
import com.carlosholanda.fitness_ai_api.infrastructure.response.ResponseCodes;

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
    public ResponseEntity<ApiResponse<LoginResponse>> login (@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authUseCases.login(request);
        return ResponseEntity.ok(ApiResponse.success(ResponseCodes.AUTH_LOGIN_SUCCESS, response, "Login successful"));
    }
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<LoginResponse>> register(@Valid @RequestBody CreateUserRequest request) {
        LoginResponse response = authUseCases.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(ResponseCodes.AUTH_REGISTER_SUCCESS, response, "User registered successfully"));
    }
    
}
