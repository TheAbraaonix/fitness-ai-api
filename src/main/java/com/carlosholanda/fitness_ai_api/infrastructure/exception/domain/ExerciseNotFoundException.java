package com.carlosholanda.fitness_ai_api.infrastructure.exception.domain;

public class ExerciseNotFoundException extends RuntimeException {
    
    public ExerciseNotFoundException(String message) {
        super(message);
    }
    
    public ExerciseNotFoundException(Long id) {
        super(String.format("Exercise with ID %d not found", id));
    }
}
