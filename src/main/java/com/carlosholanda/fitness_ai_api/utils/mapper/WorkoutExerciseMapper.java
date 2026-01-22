package com.carlosholanda.fitness_ai_api.utils.mapper;

import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout.WorkoutExerciseRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout.WorkoutExerciseResponse;
import com.carlosholanda.fitness_ai_api.domain.workout.WorkoutExercise;
import com.carlosholanda.fitness_ai_api.adapters.outbound.entities.JpaWorkoutExerciseEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ExerciseMapper.class})
public interface WorkoutExerciseMapper {
    
    // ========== DTOs ↔ Domain ==========
    
    // WorkoutExerciseRequest → WorkoutExercise (Domain)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "workoutId", ignore = true) // Will be set in service layer
    WorkoutExercise toEntity(WorkoutExerciseRequest request);
    
    // WorkoutExercise (Domain) → WorkoutExerciseResponse
    // Note: 'exercise' field will need Exercise entity loaded (handled in service)
    @Mapping(target = "exercise", ignore = true) // Will be set manually in service with full Exercise data
    WorkoutExerciseResponse toResponse(WorkoutExercise workoutExercise);
    
    // ========== Domain ↔ JPA ==========
    
    // WorkoutExercise (Domain) → JpaWorkoutExerciseEntity (Persistence)
    @Mapping(target = "workout", ignore = true) // Set manually in service layer
    @Mapping(target = "exercise", ignore = true) // Set manually in service layer
    JpaWorkoutExerciseEntity toJpaEntity(WorkoutExercise workoutExercise);
    
    // JpaWorkoutExerciseEntity (Persistence) → WorkoutExercise (Domain)
    @Mapping(source = "workout.id", target = "workoutId")
    @Mapping(source = "exercise.id", target = "exerciseId")
    WorkoutExercise toDomain(JpaWorkoutExerciseEntity entity);
}
