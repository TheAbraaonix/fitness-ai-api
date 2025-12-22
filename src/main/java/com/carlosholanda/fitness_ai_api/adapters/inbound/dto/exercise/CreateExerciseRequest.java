package com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise;

import com.carlosholanda.fitness_ai_api.domain.exercise.Difficulty;
import com.carlosholanda.fitness_ai_api.domain.exercise.MuscleGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateExerciseRequest(
        @NotNull @NotBlank
        String name,

        @NotNull
        MuscleGroup muscleGroup,

        @NotNull @NotBlank
        String instructions,

        @NotNull
        Difficulty difficulty,

        String equipmentNeeded
) {
}
