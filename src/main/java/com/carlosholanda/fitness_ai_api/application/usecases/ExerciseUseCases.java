package com.carlosholanda.fitness_ai_api.application.usecases;

import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.CreateExerciseRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.ExerciseResponse;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.UpdateExerciseRequest;
import com.carlosholanda.fitness_ai_api.domain.exercise.Difficulty;
import com.carlosholanda.fitness_ai_api.domain.exercise.MuscleGroup;

import java.util.List;

public interface ExerciseUseCases {
    ExerciseResponse create(CreateExerciseRequest request);
    ExerciseResponse update(Long id, UpdateExerciseRequest request);
    void delete(Long id);
    ExerciseResponse getById(Long id);
    List<ExerciseResponse> getAll();
    List<ExerciseResponse> getByMuscleGroup(MuscleGroup muscleGroup);
    List<ExerciseResponse> getByDifficulty(Difficulty difficulty);
    List<ExerciseResponse> getByMuscleGroupAndDifficulty(MuscleGroup muscleGroup, Difficulty difficulty);
    List<ExerciseResponse> searchByName(String name);
}
