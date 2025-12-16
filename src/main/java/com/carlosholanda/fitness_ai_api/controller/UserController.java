package com.carlosholanda.fitness_ai_api.controller;

import com.carlosholanda.fitness_ai_api.domain.user.User;
import com.carlosholanda.fitness_ai_api.dto.userDto.CreateUserRequest;
import com.carlosholanda.fitness_ai_api.dto.userDto.UpdateUserRequest;
import com.carlosholanda.fitness_ai_api.dto.userDto.UserResponse;
import com.carlosholanda.fitness_ai_api.mapper.UserMapper;
import com.carlosholanda.fitness_ai_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        User user = mapper.toEntity(request);
        User savedUser = service.create(user);
        UserResponse response = mapper.toResponse(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET /api/users - Buscar todos
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        List<User> users = service.getAll();
        List<UserResponse> responses = users.stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    // GET /api/users/email/{email} - Buscar por email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getByEmail(@PathVariable String email) {
        User user = service.getByEmail(email);
        UserResponse response = mapper.toResponse(user);
        return ResponseEntity.ok(response);
    }

    // GET /api/users/{id} - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable UUID id) {
        User user = service.getById(id);
        UserResponse response = mapper.toResponse(user);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateUserRequest request
    ) {
        User user = service.getById(id);
        mapper.updateEntityFromRequest(user, request);
        User updatedUser = service.updateUser(id, user);
        UserResponse response = mapper.toResponse(updatedUser);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
