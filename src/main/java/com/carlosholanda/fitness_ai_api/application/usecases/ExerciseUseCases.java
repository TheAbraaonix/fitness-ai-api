package com.carlosholanda.fitness_ai_api.application.usecases;

import com.carlosholanda.fitness_ai_api.domain.exercise.Difficulty;
import com.carlosholanda.fitness_ai_api.domain.exercise.Exercise;
import com.carlosholanda.fitness_ai_api.domain.exercise.MuscleGroup;

import java.util.List;
import java.util.UUID;

public interface ExerciseUseCases {
    Exercise create(Exercise exercise);
    Exercise update(UUID id, Exercise exercise);
    void delete(UUID id);
    Exercise getById(UUID id);
    List<Exercise> getAll();
    List<Exercise> getByMuscleGroup(MuscleGroup muscleGroup);
    List<Exercise> getByDifficulty(Difficulty difficulty);
    List<Exercise> getByMuscleGroupAndDifficulty(MuscleGroup muscleGroup, Difficulty difficulty);
    List<Exercise> searchByName(String name);
}
