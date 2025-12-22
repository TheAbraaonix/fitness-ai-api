package com.carlosholanda.fitness_ai_api.adapters.outbound.repositories.user;

import com.carlosholanda.fitness_ai_api.adapters.outbound.entities.JpaUserEntity;
import com.carlosholanda.fitness_ai_api.domain.user.User;
import com.carlosholanda.fitness_ai_api.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        JpaUserEntity userEntity = new JpaUserEntity(user);
        this.jpaUserRepository.save(userEntity);
        return new User(userEntity.getId(),
                userEntity.getName(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getAge(), userEntity.getWeight(),
                userEntity.getHeight(), userEntity.getAvailableDaysPerWeek(), userEntity.getFitnessGoal(), userEntity.getFitnessLevel(),
                userEntity.isActive(), userEntity.getCreatedAt(), userEntity.getUpdatedAt());
    }

    @Override
    public User update(User user) {
        JpaUserEntity userEntity = new JpaUserEntity(user);
        JpaUserEntity updatedEntity = this.jpaUserRepository.save(userEntity);
        return new User(updatedEntity.getId(),
                updatedEntity.getName(), updatedEntity.getEmail(), updatedEntity.getPassword(), updatedEntity.getAge(), updatedEntity.getWeight(),
                updatedEntity.getHeight(), updatedEntity.getAvailableDaysPerWeek(), updatedEntity.getFitnessGoal(), updatedEntity.getFitnessLevel(),
                updatedEntity.isActive(), updatedEntity.getCreatedAt(), updatedEntity.getUpdatedAt());
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
        Optional<JpaUserEntity> userEntity = this.jpaUserRepository.findById(id);
        return userEntity.map(entity -> new User(entity.getId(),
                entity.getName(), entity.getEmail(), entity.getPassword(), entity.getAge(), entity.getWeight(),
                entity.getHeight(), entity.getAvailableDaysPerWeek(), entity.getFitnessGoal(), entity.getFitnessLevel(),
                entity.isActive(), entity.getCreatedAt(), entity.getUpdatedAt()));
    }

    @Override
    public List<User> findAll() {
        return this.jpaUserRepository.findAll()
                .stream()
                .map(entity -> new User(entity.getId(),
                entity.getName(), entity.getEmail(), entity.getPassword(), entity.getAge(), entity.getWeight(),
                entity.getHeight(), entity.getAvailableDaysPerWeek(), entity.getFitnessGoal(), entity.getFitnessLevel(),
                entity.isActive(), entity.getCreatedAt(), entity.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<JpaUserEntity> userEntity = this.jpaUserRepository.findByEmail(email);
        return userEntity.map(entity -> new User(entity.getId(),
                entity.getName(), entity.getEmail(), entity.getPassword(), entity.getAge(), entity.getWeight(),
                entity.getHeight(), entity.getAvailableDaysPerWeek(), entity.getFitnessGoal(), entity.getFitnessLevel(),
                entity.isActive(), entity.getCreatedAt(), entity.getUpdatedAt()));
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.jpaUserRepository.existsByEmail(email);
    }
}
