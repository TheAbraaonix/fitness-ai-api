package com.carlosholanda.fitness_ai_api.adapters.inboud.dto.userDto;

import com.carlosholanda.fitness_ai_api.domain.user.FitnessGoal;
import com.carlosholanda.fitness_ai_api.domain.user.FitnessLevel;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,

        Integer age,
        Double weight,
        Double height,
        Integer availableDaysPerWeek,
        FitnessGoal fitnessGoal,
        FitnessLevel fitnessLevel,

        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
