package com.carlosholanda.fitness_ai_api.application.service;

import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.ExerciseResponse;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout.CreateWorkoutRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout.UpdateWorkoutRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout.WorkoutExerciseResponse;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout.WorkoutResponse;
import com.carlosholanda.fitness_ai_api.application.usecases.WorkoutUseCases;
import com.carlosholanda.fitness_ai_api.domain.exercise.Exercise;
import com.carlosholanda.fitness_ai_api.domain.exercise.ExerciseRepository;
import com.carlosholanda.fitness_ai_api.domain.workout.Workout;
import com.carlosholanda.fitness_ai_api.domain.workout.WorkoutExercise;
import com.carlosholanda.fitness_ai_api.domain.workout.WorkoutRepository;
import com.carlosholanda.fitness_ai_api.infrastructure.exception.domain.exercise.ExerciseNotFoundException;
import com.carlosholanda.fitness_ai_api.infrastructure.exception.domain.workout.WorkoutNotFoundException;
import com.carlosholanda.fitness_ai_api.utils.mapper.ExerciseMapper;
import com.carlosholanda.fitness_ai_api.utils.mapper.WorkoutExerciseMapper;
import com.carlosholanda.fitness_ai_api.utils.mapper.WorkoutMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutUseCases {
    private final WorkoutRepository repository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutMapper mapper;
    private final WorkoutExerciseMapper workoutExerciseMapper;
    private final ExerciseMapper exerciseMapper;

    public WorkoutServiceImpl(
            WorkoutRepository repository,
            ExerciseRepository exerciseRepository,
            WorkoutMapper mapper,
            WorkoutExerciseMapper workoutExerciseMapper,
            ExerciseMapper exerciseMapper) {
        this.repository = repository;
        this.exerciseRepository = exerciseRepository;
        this.mapper = mapper;
        this.workoutExerciseMapper = workoutExerciseMapper;
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public WorkoutResponse create(CreateWorkoutRequest request) {
        // Convert DTO to Domain
        Workout workout = mapper.toEntity(request);
        
        // Convert WorkoutExerciseRequest list to WorkoutExercise list
        if (request.exercises() != null) {
            List<WorkoutExercise> exercises = request.exercises().stream()
                    .map(workoutExerciseMapper::toEntity)
                    .collect(Collectors.toList());
            workout.setExercises(exercises);
        }
        
        // Save (Repository handles validation and relationships)
        Workout savedWorkout = repository.save(workout);
        
        // Convert to Response with full exercise data
        return toResponseWithExercises(savedWorkout);
    }

    @Override
    public WorkoutResponse update(Long id, UpdateWorkoutRequest request) {
        // Find existing workout
        Workout existingWorkout = repository.findById(id)
                .orElseThrow(() -> new WorkoutNotFoundException(id));

        // Apply changes from request (only non-null fields)
        mapper.updateEntityFromRequest(request, existingWorkout);
        
        // Update exercises if provided
        if (request.exercises() != null) {
            List<WorkoutExercise> updatedExercises = request.exercises().stream()
                    .map(workoutExerciseMapper::toEntity)
                    .collect(Collectors.toList());
            existingWorkout.setExercises(updatedExercises);
        }
        
        // Save
        Workout updatedWorkout = repository.update(existingWorkout);
        
        // Convert to Response
        return toResponseWithExercises(updatedWorkout);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public WorkoutResponse getById(Long id) {
        // Always fetch with exercises to avoid N+1 problem
        Workout workout = repository.findByIdWithExercises(id)
                .orElseThrow(() -> new WorkoutNotFoundException(id));
        
        return toResponseWithExercises(workout);
    }

    @Override
    public List<WorkoutResponse> getByUserId(UUID userId) {
        List<Workout> workouts = repository.findByUserId(userId);
        
        return workouts.stream()
                .map(this::toResponseWithExercises)
                .collect(Collectors.toList());
    }
    
    /**
     * Converts Workout domain to WorkoutResponse with full exercise data
     */
    private WorkoutResponse toResponseWithExercises(Workout workout) {
        // Convert base workout
        WorkoutResponse baseResponse = mapper.toResponse(workout);
        
        // Convert exercises with full Exercise entity data
        List<WorkoutExerciseResponse> exercisesResponse = new ArrayList<>();
        
        if (workout.getExercises() != null) {
            for (WorkoutExercise we : workout.getExercises()) {
                // Fetch full exercise entity
                Exercise exercise = exerciseRepository.findById(we.getExerciseId())
                        .orElseThrow(() -> new ExerciseNotFoundException(we.getExerciseId()));
                
                // Convert to response
                ExerciseResponse exerciseResponse = exerciseMapper.toResponse(exercise);
                
                // Build WorkoutExerciseResponse with full exercise data
                WorkoutExerciseResponse weResponse = new WorkoutExerciseResponse(
                        we.getId(),
                        exerciseResponse,  // Full exercise object
                        we.getSets(),
                        we.getReps(),
                        we.getWeight(),
                        we.getRestTime(),
                        we.getNotes(),
                        we.getOrderInWorkout()
                );
                
                exercisesResponse.add(weResponse);
            }
        }
        
        // Return response with exercises
        return new WorkoutResponse(
                baseResponse.id(),
                baseResponse.userId(),
                baseResponse.name(),
                baseResponse.type(),
                baseResponse.description(),
                baseResponse.status(),
                baseResponse.aiGenerated(),
                exercisesResponse,  // Full exercises with nested Exercise data
                baseResponse.createdAt(),
                baseResponse.updatedAt()
        );
    }
}
