package com.carlosholanda.fitness_ai_api.application.service;

import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.user.CreateUserRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.user.UpdateUserRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.user.UserResponse;
import com.carlosholanda.fitness_ai_api.application.usecases.UserUseCases;
import com.carlosholanda.fitness_ai_api.domain.user.User;
import com.carlosholanda.fitness_ai_api.domain.user.UserRepository;
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
            throw new RuntimeException("Email já cadastrado: " + user.getEmail());
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
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
        return mapper.toResponse(user);
    }

    @Override
    public UserResponse getByEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o e-mail: " + email));
        return mapper.toResponse(user);
    }

    @Override
    public UserResponse update(UUID id, UpdateUserRequest request) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));

        if (request.email() != null && !existingUser.getEmail().equals(request.email())) {
            if (repository.existsByEmail(request.email())) {
                throw new RuntimeException("Email já cadastrado: " + request.email());
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
