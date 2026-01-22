package com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record WorkoutExerciseRequest(
        @NotNull(message = "Exercise ID is required")
        Long exerciseId,

        @NotNull(message = "Sets is required")
        @Positive(message = "Sets must be greater than zero")
        Integer sets,

        @NotNull(message = "Reps is required")
        @Positive(message = "Reps must be greater than zero")
        Integer reps,

        Double weight,

        Integer restTime,

        String notes,

        @NotNull(message = "Order in workout is required")
        @Positive(message = "Order must be greater than zero")
        Integer orderInWorkout
) {
}
