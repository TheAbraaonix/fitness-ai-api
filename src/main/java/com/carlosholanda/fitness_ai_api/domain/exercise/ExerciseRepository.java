package com.carlosholanda.fitness_ai_api.domain.exercise;

import com.carlosholanda.fitness_ai_api.adapters.outbound.entities.JpaExerciseEntity;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository {
    Exercise save(Exercise exercise);
    Exercise update(Exercise exercise);
    void delete(Long id);
    List<Exercise> findAll();
    Optional<Exercise> findById(Long id);
    List<Exercise> findByMuscleGroup(MuscleGroup muscleGroup);
    List<Exercise> findByDifficulty(Difficulty difficulty);
    List<Exercise> findByMuscleGroupAndDifficulty(MuscleGroup muscleGroup, Difficulty difficulty);
    List<Exercise> findByNameContainingIgnoreCase(String name);
    boolean existsByName(String name);
}
