package com.carlosholanda.fitness_ai_api.adapters.outbound.repositories.exercise;

import com.carlosholanda.fitness_ai_api.adapters.outbound.entities.JpaExerciseEntity;
import com.carlosholanda.fitness_ai_api.domain.exercise.Difficulty;
import com.carlosholanda.fitness_ai_api.domain.exercise.Exercise;
import com.carlosholanda.fitness_ai_api.domain.exercise.ExerciseRepository;
import com.carlosholanda.fitness_ai_api.domain.exercise.MuscleGroup;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ExerciseRepositoryImpl implements ExerciseRepository {
    private final JpaExerciseRepository jpaExerciseRepository;

    public ExerciseRepositoryImpl(JpaExerciseRepository jpaExerciseRepository) {
        this.jpaExerciseRepository = jpaExerciseRepository;
    }

    @Override
    public Exercise save(Exercise exercise) {
        JpaExerciseEntity jpaExerciseEntity = new JpaExerciseEntity(exercise);
        this.jpaExerciseRepository.save(jpaExerciseEntity);
        return new Exercise(jpaExerciseEntity.getId(), jpaExerciseEntity.getName(),
                jpaExerciseEntity.getMuscleGroup(), jpaExerciseEntity.getInstructions(), jpaExerciseEntity.getDifficulty(), jpaExerciseEntity.getEquipmentNeeded(),
                jpaExerciseEntity.isActive(), jpaExerciseEntity.getCreatedAt(), jpaExerciseEntity.getUpdatedAt());
    }

    @Override
    public Exercise update(Exercise exercise) {
        JpaExerciseEntity jpaExerciseEntity = new JpaExerciseEntity(exercise);
        JpaExerciseEntity updatedEntity = this.jpaExerciseRepository.save(jpaExerciseEntity);
        return new Exercise(updatedEntity.getId(), updatedEntity.getName(),
                updatedEntity.getMuscleGroup(), updatedEntity.getInstructions(), updatedEntity.getDifficulty(), updatedEntity.getEquipmentNeeded(),
                updatedEntity.isActive(), updatedEntity.getCreatedAt(), updatedEntity.getUpdatedAt());
    }

    @Override
    public void delete(Long id) {
        JpaExerciseEntity jpaExerciseEntity = this.jpaExerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found with ID: " + id));
        jpaExerciseEntity.setActive(false);
        this.jpaExerciseRepository.save(jpaExerciseEntity);
    }

    @Override
    public List<Exercise> findAll() {
        return this.jpaExerciseRepository.findAll()
                .stream()
                .map(entity -> new Exercise(entity.getId(), entity.getName(),
                    entity.getMuscleGroup(), entity.getInstructions(), entity.getDifficulty(), entity.getEquipmentNeeded(),
                    entity.isActive(), entity.getCreatedAt(), entity.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Exercise> findById(Long id) {
        Optional<JpaExerciseEntity> jpaExerciseEntity = this.jpaExerciseRepository.findById(id);
        return jpaExerciseEntity.map(entity -> new Exercise(entity.getId(), entity.getName(),
                entity.getMuscleGroup(), entity.getInstructions(), entity.getDifficulty(), entity.getEquipmentNeeded(),
                entity.isActive(), entity.getCreatedAt(), entity.getUpdatedAt()));
    }

    @Override
    public List<Exercise> findByMuscleGroup(MuscleGroup muscleGroup) {
        return this.jpaExerciseRepository.findByMuscleGroup(muscleGroup)
                .stream()
                .map(entity -> new Exercise(entity.getId(), entity.getName(),
                        entity.getMuscleGroup(), entity.getInstructions(), entity.getDifficulty(), entity.getEquipmentNeeded(),
                        entity.isActive(), entity.getCreatedAt(), entity.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Exercise> findByDifficulty(Difficulty difficulty) {
        return this.jpaExerciseRepository.findByDifficulty(difficulty)
                .stream()
                .map(entity -> new Exercise(entity.getId(), entity.getName(),
                        entity.getMuscleGroup(), entity.getInstructions(), entity.getDifficulty(), entity.getEquipmentNeeded(),
                        entity.isActive(), entity.getCreatedAt(), entity.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Exercise> findByMuscleGroupAndDifficulty(MuscleGroup muscleGroup, Difficulty difficulty) {
        return this.jpaExerciseRepository.findByMuscleGroupAndDifficulty(muscleGroup, difficulty)
                .stream()
                .map(entity -> new Exercise(entity.getId(), entity.getName(),
                        entity.getMuscleGroup(), entity.getInstructions(), entity.getDifficulty(), entity.getEquipmentNeeded(),
                        entity.isActive(), entity.getCreatedAt(), entity.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Exercise> findByNameContainingIgnoreCase(String name) {
        return this.jpaExerciseRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(entity -> new Exercise(entity.getId(), entity.getName(),
                        entity.getMuscleGroup(), entity.getInstructions(), entity.getDifficulty(), entity.getEquipmentNeeded(),
                        entity.isActive(), entity.getCreatedAt(), entity.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByName(String name) {
        return this.jpaExerciseRepository.existsByName(name);
    }
}
