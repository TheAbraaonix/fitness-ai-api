package com.carlosholanda.fitness_ai_api.utils.mapper;

import com.carlosholanda.fitness_ai_api.domain.user.User;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.user.CreateUserRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.user.UpdateUserRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.user.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    // CreateUserRequest → User (Entity)
    public User toEntity(CreateUserRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setAge(request.age());
        user.setWeight(request.weight());
        user.setHeight(request.height());
        user.setAvailableDaysPerWeek(request.availableDaysPerWeek());
        user.setFitnessGoal(request.fitnessGoal());
        user.setFitnessLevel(request.fitnessLevel());
        return user;
    }

    // UpdateUserRequest → User (atualizar campos)
    public void updateEntityFromRequest(User user, UpdateUserRequest request) {
        if (request.name() != null) {
            user.setName(request.name());
        }
        if (request.email() != null) {
            user.setEmail(request.email());
        }
        if (request.age() != null) {
            user.setAge(request.age());
        }
        if (request.weight() != null) {
            user.setWeight(request.weight());
        }
        if (request.height() != null) {
            user.setHeight(request.height());
        }
        if (request.availableDaysPerWeek() != null) {
            user.setAvailableDaysPerWeek(request.availableDaysPerWeek());
        }
        if (request.fitnessGoal() != null) {
            user.setFitnessGoal(request.fitnessGoal());
        }
        if (request.fitnessLevel() != null) {
            user.setFitnessLevel(request.fitnessLevel());
        }
    }

    // User (Entity) → UserResponse
    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getWeight(),
                user.getHeight(),
                user.getAvailableDaysPerWeek(),
                user.getFitnessGoal(),
                user.getFitnessLevel(),
                user.isActive(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
