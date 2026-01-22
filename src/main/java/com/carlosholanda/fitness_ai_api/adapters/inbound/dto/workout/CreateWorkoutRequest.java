package com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

import com.carlosholanda.fitness_ai_api.domain.workout.enums.WorkoutType;

public record CreateWorkoutRequest(
        @NotNull(message = "User ID is required")
        UUID userId,

        @NotNull(message = "Name is required")
        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotNull(message = "Workout type is required")
        WorkoutType type,

        String description,

        @NotNull(message = "Exercises list is required")
        @Valid
        List<WorkoutExerciseRequest> exercises
) {
}
