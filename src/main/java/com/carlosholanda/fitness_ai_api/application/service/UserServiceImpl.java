package com.carlosholanda.fitness_ai_api.application.service;

import com.carlosholanda.fitness_ai_api.application.usecases.UserUseCases;
import com.carlosholanda.fitness_ai_api.domain.user.User;
import com.carlosholanda.fitness_ai_api.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserUseCases {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        if(repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email já cadastrado: " + user.getEmail());
        }

        return repository.save(user);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }

    public User getByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o e-mail: " + email));
    }

    public User updateUser(UUID id, User updatedUser) {
        User existingUser = getById(id);

        if (!existingUser.getEmail().equals(updatedUser.getEmail())) {
            if (repository.existsByEmail(updatedUser.getEmail())) {
                throw new RuntimeException("Email já cadastrado: " + updatedUser.getEmail());
            }
        }

        // Atualizar campos
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAge(updatedUser.getAge());
        existingUser.setWeight(updatedUser.getWeight());
        existingUser.setHeight(updatedUser.getHeight());
        existingUser.setAvailableDaysPerWeek(updatedUser.getAvailableDaysPerWeek());
        existingUser.setFitnessGoal(updatedUser.getFitnessGoal());
        existingUser.setFitnessLevel(updatedUser.getFitnessLevel());

        return repository.update(existingUser);
    }

    public void delete(UUID id) {
        repository.delete(id);
    }
}
