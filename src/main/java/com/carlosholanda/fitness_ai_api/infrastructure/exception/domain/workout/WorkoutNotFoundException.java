package com.carlosholanda.fitness_ai_api.infrastructure.exception.domain.workout;

public class WorkoutNotFoundException extends RuntimeException {
    
    public WorkoutNotFoundException(String message) {
        super(message);
    }
    
    public WorkoutNotFoundException(Long id) {
        super(String.format("Workout with ID %d not found", id));
    }

}
