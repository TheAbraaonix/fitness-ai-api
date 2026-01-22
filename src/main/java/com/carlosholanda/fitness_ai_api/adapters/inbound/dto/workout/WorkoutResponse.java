package com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout;

import com.carlosholanda.fitness_ai_api.domain.workout.enums.WorkoutStatus;
import com.carlosholanda.fitness_ai_api.domain.workout.enums.WorkoutType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record WorkoutResponse(
        Long id,
        UUID userId,
        String name,
        WorkoutType type,
        String description,
        WorkoutStatus status,
        Boolean aiGenerated,
        List<WorkoutExerciseResponse> exercises,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
