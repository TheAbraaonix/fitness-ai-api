package com.carlosholanda.fitness_ai_api.adapters.outbound.repositories.user;

import com.carlosholanda.fitness_ai_api.adapters.outbound.entities.JpaUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<JpaUserEntity, UUID> {
    Optional<JpaUserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
