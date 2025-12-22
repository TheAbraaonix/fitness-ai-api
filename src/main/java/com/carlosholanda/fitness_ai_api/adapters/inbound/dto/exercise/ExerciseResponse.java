package com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise;

import com.carlosholanda.fitness_ai_api.domain.exercise.Difficulty;
import com.carlosholanda.fitness_ai_api.domain.exercise.MuscleGroup;

import java.time.LocalDateTime;
import java.util.UUID;

public record ExerciseResponse(
        UUID id,
        String name,
        MuscleGroup muscleGroup,
        String instructions,
        Difficulty difficulty,
        String equipmentNeeded,
        boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
