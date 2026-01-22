package com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout;

import com.carlosholanda.fitness_ai_api.domain.workout.enums.WorkoutStatus;
import com.carlosholanda.fitness_ai_api.domain.workout.enums.WorkoutType;

import jakarta.validation.Valid;

import java.util.List;

public record UpdateWorkoutRequest(
        String name,
        WorkoutType type,
        String description,
        WorkoutStatus status,
        @Valid
        List<WorkoutExerciseRequest> exercises
) {
}
