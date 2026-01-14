package com.carlosholanda.fitness_ai_api.application.usecases;

import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.auth.CreateUserRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.auth.LoginRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.auth.LoginResponse;

public interface AuthUseCases {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse register(CreateUserRequest createUserRequest);
}
