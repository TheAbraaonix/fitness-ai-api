package com.carlosholanda.fitness_ai_api.adapters.inbound.controller;

import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout.CreateWorkoutRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout.UpdateWorkoutRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout.WorkoutResponse;
import com.carlosholanda.fitness_ai_api.application.usecases.WorkoutUseCases;
import com.carlosholanda.fitness_ai_api.infrastructure.response.ApiResponse;
import com.carlosholanda.fitness_ai_api.infrastructure.response.ResponseCodes;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workouts")
@PreAuthorize("hasRole('USER')")
public class WorkoutController {
    private final WorkoutUseCases workoutUseCases;

    public WorkoutController(WorkoutUseCases workoutUseCases) {
        this.workoutUseCases = workoutUseCases;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WorkoutResponse>> create(@Valid @RequestBody CreateWorkoutRequest request) {
        WorkoutResponse response = workoutUseCases.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(ResponseCodes.WORKOUT_CREATED, response, "Workout created successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WorkoutResponse>> getById(@PathVariable Long id) {
        WorkoutResponse response = workoutUseCases.getById(id);
        return ResponseEntity.ok(ApiResponse.success(ResponseCodes.WORKOUT_RETRIEVED, response, "Workout retrieved successfully"));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<WorkoutResponse>>> getByUserId(@PathVariable UUID userId) {
        List<WorkoutResponse> responses = workoutUseCases.getByUserId(userId);
        return ResponseEntity.ok(ApiResponse.success(ResponseCodes.WORKOUTS_RETRIEVED, responses, "Workouts retrieved successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WorkoutResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateWorkoutRequest request
    ) {
        WorkoutResponse response = workoutUseCases.update(id, request);
        return ResponseEntity.ok(ApiResponse.success(ResponseCodes.WORKOUT_UPDATED, response, "Workout updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        workoutUseCases.delete(id);
        return ResponseEntity.ok(ApiResponse.success(ResponseCodes.WORKOUT_DELETED, "Workout deleted successfully"));
    }
}
