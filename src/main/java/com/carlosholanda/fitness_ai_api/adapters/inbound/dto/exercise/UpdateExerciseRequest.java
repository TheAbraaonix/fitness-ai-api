package com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise;

import com.carlosholanda.fitness_ai_api.domain.exercise.Difficulty;
import com.carlosholanda.fitness_ai_api.domain.exercise.MuscleGroup;

public record UpdateExerciseRequest(
        String name,
        MuscleGroup muscleGroup,
        String instructions,
        Difficulty difficulty,
        String equipmentNeeded
) {
}
