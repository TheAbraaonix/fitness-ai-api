package com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise;

import com.carlosholanda.fitness_ai_api.domain.exercise.enums.Difficulty;
import com.carlosholanda.fitness_ai_api.domain.exercise.enums.MuscleGroup;

import java.time.LocalDateTime;

public record ExerciseResponse(
        Long id,
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
