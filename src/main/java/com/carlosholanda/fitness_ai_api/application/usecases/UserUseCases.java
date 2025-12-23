package com.carlosholanda.fitness_ai_api.application.usecases;

import com.carlosholanda.fitness_ai_api.domain.user.User;

import java.util.List;
import java.util.UUID;

public interface UserUseCases {
    User create(User user);
    List<User> getAll();
    User getById(UUID id);
    User getByEmail(String email);
    User update(UUID id, User user);
    void delete(UUID id);
}
