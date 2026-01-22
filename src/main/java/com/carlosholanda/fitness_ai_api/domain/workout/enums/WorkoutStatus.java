package com.carlosholanda.fitness_ai_api.domain.workout.enums;

public enum WorkoutStatus {
    DRAFT,      // Workout without exercises or in creation
    ACTIVE,     // Workout ready to be used
    COMPLETED,  // Workout execution finished
    ARCHIVED,   // Workout archived by user
    CANCELLED,  // Workout cancelled
    PLANNED     // Workout scheduled for future
}
