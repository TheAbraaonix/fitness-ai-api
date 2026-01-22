package com.carlosholanda.fitness_ai_api.adapters.outbound.repositories.workout;

import com.carlosholanda.fitness_ai_api.adapters.outbound.entities.JpaUserEntity;
import com.carlosholanda.fitness_ai_api.adapters.outbound.entities.JpaWorkoutEntity;
import com.carlosholanda.fitness_ai_api.adapters.outbound.entities.JpaWorkoutExerciseEntity;
import com.carlosholanda.fitness_ai_api.adapters.outbound.entities.JpaExerciseEntity;
import com.carlosholanda.fitness_ai_api.adapters.outbound.repositories.user.JpaUserRepository;
import com.carlosholanda.fitness_ai_api.adapters.outbound.repositories.exercise.JpaExerciseRepository;
import com.carlosholanda.fitness_ai_api.domain.workout.Workout;
import com.carlosholanda.fitness_ai_api.domain.workout.WorkoutRepository;
import com.carlosholanda.fitness_ai_api.infrastructure.exception.domain.exercise.ExerciseNotFoundException;
import com.carlosholanda.fitness_ai_api.infrastructure.exception.domain.user.UserNotFoundException;
import com.carlosholanda.fitness_ai_api.infrastructure.exception.domain.workout.WorkoutNotFoundException;
import com.carlosholanda.fitness_ai_api.utils.mapper.WorkoutMapper;
import com.carlosholanda.fitness_ai_api.utils.mapper.WorkoutExerciseMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class WorkoutRepositoryImpl implements WorkoutRepository {
    private final JpaWorkoutRepository jpaWorkoutRepository;
    private final JpaUserRepository jpaUserRepository;
    private final JpaExerciseRepository jpaExerciseRepository;
    private final WorkoutMapper workoutMapper;
    private final WorkoutExerciseMapper workoutExerciseMapper;

    public WorkoutRepositoryImpl(
            JpaWorkoutRepository jpaWorkoutRepository,
            JpaUserRepository jpaUserRepository,
            JpaExerciseRepository jpaExerciseRepository,
            WorkoutMapper workoutMapper,
            WorkoutExerciseMapper workoutExerciseMapper) {
        this.jpaWorkoutRepository = jpaWorkoutRepository;
        this.jpaUserRepository = jpaUserRepository;
        this.jpaExerciseRepository = jpaExerciseRepository;
        this.workoutMapper = workoutMapper;
        this.workoutExerciseMapper = workoutExerciseMapper;
    }

    @Override
    @Transactional
    public Workout save(Workout workout) {
        // Find user entity
        JpaUserEntity user = jpaUserRepository.findById(workout.getUserId())
                .orElseThrow(() -> new UserNotFoundException(workout.getUserId()));

        // Map domain to JPA entity
        JpaWorkoutEntity workoutEntity = workoutMapper.toJpaEntity(workout);
        
		workoutEntity.setUser(user);

        // Map and set exercises with relationships
        if (workout.getExercises() != null && !workout.getExercises().isEmpty()) {
            List<JpaWorkoutExerciseEntity> exerciseEntities = workout.getExercises().stream()
                    .map(we -> {
                        JpaWorkoutExerciseEntity entity = workoutExerciseMapper.toJpaEntity(we);
                        
						entity.setWorkout(workoutEntity);
                        
                        // Find exercise entity
                        JpaExerciseEntity exercise = jpaExerciseRepository.findById(we.getExerciseId())
                                .orElseThrow(() -> new ExerciseNotFoundException(we.getExerciseId()));
                        
						entity.setExercise(exercise);
                        
                        return entity;
                    })
                    .toList();
            
            workoutEntity.setExercises(exerciseEntities);
        }

        // Save and return
        JpaWorkoutEntity savedEntity = jpaWorkoutRepository.save(workoutEntity);
        
		return workoutMapper.toDomain(savedEntity);
    }

    @Override
    @Transactional
    public Workout update(Workout workout) {
        // Find existing workout
        JpaWorkoutEntity existingWorkout = jpaWorkoutRepository.findById(workout.getId())
                .orElseThrow(() -> new WorkoutNotFoundException(workout.getId()));

        // Update basic fields
        existingWorkout.setName(workout.getName());
        existingWorkout.setType(workout.getType());
        existingWorkout.setDescription(workout.getDescription());
        existingWorkout.setStatus(workout.getStatus());
        existingWorkout.setAiGenerated(workout.isAiGenerated());

        // Update exercises (orphanRemoval will delete removed ones)
        if (workout.getExercises() != null) {
            // Clear existing exercises
            existingWorkout.getExercises().clear();
            
            // Add new exercises
            List<JpaWorkoutExerciseEntity> newExercises = workout.getExercises().stream()
                    .map(we -> {
                        JpaWorkoutExerciseEntity entity = workoutExerciseMapper.toJpaEntity(we);
                        
						entity.setWorkout(existingWorkout);
                        
                        JpaExerciseEntity exercise = jpaExerciseRepository.findById(we.getExerciseId())
                                .orElseThrow(() -> new ExerciseNotFoundException(we.getExerciseId()));
                        
						entity.setExercise(exercise);
                        
                        return entity;
                    })
                    .toList();
            
            existingWorkout.getExercises().addAll(newExercises);
        }

        JpaWorkoutEntity updatedEntity = jpaWorkoutRepository.save(existingWorkout);
        
		return workoutMapper.toDomain(updatedEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        JpaWorkoutEntity entity = jpaWorkoutRepository.findById(id)
                .orElseThrow(() -> new WorkoutNotFoundException(id));
        jpaWorkoutRepository.delete(entity);
    }

    @Override
    public Optional<Workout> findById(Long id) {
        return jpaWorkoutRepository.findById(id)
                .map(workoutMapper::toDomain);
    }

    @Override
    public Optional<Workout> findByIdWithExercises(Long id) {
        return jpaWorkoutRepository.findByIdWithExercises(id)
                .map(workoutMapper::toDomain);
    }

    @Override
    public List<Workout> findByUserId(UUID userId) {
        return jpaWorkoutRepository.findByUserId(userId).stream()
                .map(workoutMapper::toDomain)
                .toList();
    }

    @Override
    public List<Workout> findAll() {
        return jpaWorkoutRepository.findAll().stream()
                .map(workoutMapper::toDomain)
                .toList();
    }
}
