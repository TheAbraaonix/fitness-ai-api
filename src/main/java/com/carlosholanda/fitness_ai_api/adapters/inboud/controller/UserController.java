package com.carlosholanda.fitness_ai_api.adapters.inboud.controller;

import com.carlosholanda.fitness_ai_api.domain.user.User;
import com.carlosholanda.fitness_ai_api.adapters.inboud.dto.userDto.CreateUserRequest;
import com.carlosholanda.fitness_ai_api.adapters.inboud.dto.userDto.UpdateUserRequest;
import com.carlosholanda.fitness_ai_api.adapters.inboud.dto.userDto.UserResponse;
import com.carlosholanda.fitness_ai_api.utils.mapper.UserMapper;
import com.carlosholanda.fitness_ai_api.application.usecases.UserUseCases;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserUseCases userUseCases;
    private final UserMapper mapper;

    public UserController(UserUseCases userUseCases, UserMapper mapper) {
        this.userUseCases = userUseCases;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        User user = mapper.toEntity(request);
        User savedUser = userUseCases.create(user);
        UserResponse response = mapper.toResponse(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET /api/users - Buscar todos
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        List<User> users = userUseCases.getAll();
        List<UserResponse> responses = users.stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    // GET /api/users/email/{email} - Buscar por email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getByEmail(@PathVariable String email) {
        User user = userUseCases.getByEmail(email);
        UserResponse response = mapper.toResponse(user);
        return ResponseEntity.ok(response);
    }

    // GET /api/users/{id} - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable UUID id) {
        User user = userUseCases.getById(id);
        UserResponse response = mapper.toResponse(user);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateUserRequest request
    ) {
        User user = userUseCases.getById(id);
        mapper.updateEntityFromRequest(user, request);
        User updatedUser = userUseCases.updateUser(id, user);
        UserResponse response = mapper.toResponse(updatedUser);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userUseCases.delete(id);
        return ResponseEntity.noContent().build();
    }
}
