package com.carlosholanda.fitness_ai_api.application.usecases;

import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout.CreateWorkoutRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout.UpdateWorkoutRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout.WorkoutResponse;
import com.carlosholanda.fitness_ai_api.domain.workout.enums.WorkoutStatus;
import com.carlosholanda.fitness_ai_api.domain.workout.enums.WorkoutType;

import java.util.List;
import java.util.UUID;

public interface WorkoutUseCases {
    WorkoutResponse create(CreateWorkoutRequest request);
    WorkoutResponse update(Long id, UpdateWorkoutRequest request);
    void delete(Long id);
    WorkoutResponse getById(Long id);
    List<WorkoutResponse> getByUserId(UUID userId);
}
