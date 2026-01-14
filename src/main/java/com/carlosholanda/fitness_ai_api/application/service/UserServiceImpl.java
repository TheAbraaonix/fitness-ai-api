package com.carlosholanda.fitness_ai_api.application.service;

import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.auth.CreateUserRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.user.UpdateUserRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.user.UserResponse;
import com.carlosholanda.fitness_ai_api.application.usecases.UserUseCases;
import com.carlosholanda.fitness_ai_api.domain.user.User;
import com.carlosholanda.fitness_ai_api.domain.user.UserRepository;
import com.carlosholanda.fitness_ai_api.infrastructure.exception.domain.UserAlreadyExistsException;
import com.carlosholanda.fitness_ai_api.infrastructure.exception.domain.UserNotFoundException;
import com.carlosholanda.fitness_ai_api.utils.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserUseCases {
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserResponse create(CreateUserRequest request) {
        User user = mapper.toEntity(request);
        
        if(repository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail());
        }

        User savedUser = repository.save(user);
        return mapper.toResponse(savedUser);
    }

    @Override
    public List<UserResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse getById(UUID id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id", id.toString()));
        return mapper.toResponse(user);
    }

    @Override
    public UserResponse getByEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email", email));
        return mapper.toResponse(user);
    }

    @Override
    public UserResponse update(UUID id, UpdateUserRequest request) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id", id.toString()));

        if (request.email() != null && !existingUser.getEmail().equals(request.email())) {
            if (repository.existsByEmail(request.email())) {
                throw new UserAlreadyExistsException(request.email());
            }
        }

        mapper.updateEntityFromRequest(request, existingUser);
        User updatedUser = repository.update(existingUser);
        return mapper.toResponse(updatedUser);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }
}
