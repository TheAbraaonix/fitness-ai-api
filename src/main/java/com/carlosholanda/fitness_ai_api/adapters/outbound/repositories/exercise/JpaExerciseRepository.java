package com.carlosholanda.fitness_ai_api.adapters.outbound.repositories.exercise;

import com.carlosholanda.fitness_ai_api.adapters.outbound.entities.JpaExerciseEntity;
import com.carlosholanda.fitness_ai_api.domain.exercise.Difficulty;
import com.carlosholanda.fitness_ai_api.domain.exercise.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaExerciseRepository extends JpaRepository<JpaExerciseEntity, UUID> {
    List<JpaExerciseEntity> findByMuscleGroup(MuscleGroup muscleGroup);
    List<JpaExerciseEntity> findByDifficulty(Difficulty difficulty);
    List<JpaExerciseEntity> findByMuscleGroupAndDifficulty(MuscleGroup muscleGroup, Difficulty difficulty);
    List<JpaExerciseEntity> findByNameContainingIgnoreCase(String name);
    boolean existsByName(String name);
}
