package com.carlosholanda.fitness_ai_api.infrastructure.exception.domain.workout;

public class WorkoutCannotBeActivatedException extends RuntimeException {
    public WorkoutCannotBeActivatedException(Long workoutId) {
        super("Workout with ID " + workoutId + " cannot be activated without exercises");
    }
}
