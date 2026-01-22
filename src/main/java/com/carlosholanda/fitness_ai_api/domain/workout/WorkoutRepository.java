package com.carlosholanda.fitness_ai_api.domain.workout;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkoutRepository {
    Workout save(Workout workout);
    Workout update(Workout workout);
    void delete(Long id);
    Optional<Workout> findById(Long id);
    Optional<Workout> findByIdWithExercises(Long id);
    List<Workout> findByUserId(UUID userId);
    List<Workout> findAll();
}
