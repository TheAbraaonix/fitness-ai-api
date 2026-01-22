package com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout;

import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.ExerciseResponse;

public record WorkoutExerciseResponse(
        Long id,
        ExerciseResponse exercise,
        Integer sets,
        Integer reps,
        Double weight,
        Integer restTime,
        String notes,
        Integer orderInWorkout
) {
}
