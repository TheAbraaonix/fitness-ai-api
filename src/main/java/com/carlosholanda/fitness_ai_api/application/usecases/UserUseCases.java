package com.carlosholanda.fitness_ai_api.application.usecases;

import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.user.CreateUserRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.user.UpdateUserRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.user.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserUseCases {
    UserResponse create(CreateUserRequest request);
    List<UserResponse> getAll();
    UserResponse getById(UUID id);
    UserResponse getByEmail(String email);
    UserResponse update(UUID id, UpdateUserRequest request);
    void delete(UUID id);
}
