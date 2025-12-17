package com.carlosholanda.fitness_ai_api.domain.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    User update(User user);
    void delete(UUID id);
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    boolean existsByEmail(String email);
}
