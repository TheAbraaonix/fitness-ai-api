package com.carlosholanda.fitness_ai_api.adapters.outbound.repositories.user;

import com.carlosholanda.fitness_ai_api.adapters.outbound.entities.JpaUserEntity;
import com.carlosholanda.fitness_ai_api.domain.user.User;
import com.carlosholanda.fitness_ai_api.domain.user.UserRepository;
import com.carlosholanda.fitness_ai_api.utils.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;
    private final UserMapper mapper;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository, UserMapper mapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        JpaUserEntity userEntity = mapper.toJpaEntity(user);
        JpaUserEntity savedEntity = this.jpaUserRepository.save(userEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public User update(User user) {
        JpaUserEntity userEntity = mapper.toJpaEntity(user);
        JpaUserEntity updatedEntity = this.jpaUserRepository.save(userEntity);
        return mapper.toDomain(updatedEntity);
    }

    @Override
    public void delete(UUID id) {
        JpaUserEntity entity = this.jpaUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
        entity.setActive(false);
        this.jpaUserRepository.save(entity);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return this.jpaUserRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return this.jpaUserRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.jpaUserRepository.findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.jpaUserRepository.existsByEmail(email);
    }
}
