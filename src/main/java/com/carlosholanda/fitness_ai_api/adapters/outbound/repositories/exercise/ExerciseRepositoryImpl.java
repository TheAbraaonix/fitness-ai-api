package com.carlosholanda.fitness_ai_api.adapters.outbound.repositories.exercise;

import com.carlosholanda.fitness_ai_api.adapters.outbound.entities.JpaExerciseEntity;
import com.carlosholanda.fitness_ai_api.domain.exercise.Difficulty;
import com.carlosholanda.fitness_ai_api.domain.exercise.Exercise;
import com.carlosholanda.fitness_ai_api.domain.exercise.ExerciseRepository;
import com.carlosholanda.fitness_ai_api.domain.exercise.MuscleGroup;
import com.carlosholanda.fitness_ai_api.utils.mapper.ExerciseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ExerciseRepositoryImpl implements ExerciseRepository {
    private final JpaExerciseRepository jpaExerciseRepository;
    private final ExerciseMapper mapper;

    public ExerciseRepositoryImpl(JpaExerciseRepository jpaExerciseRepository, ExerciseMapper mapper) {
        this.jpaExerciseRepository = jpaExerciseRepository;
        this.mapper = mapper;
    }

    @Override
    public Exercise save(Exercise exercise) {
        JpaExerciseEntity jpaExerciseEntity = mapper.toJpaEntity(exercise);
        JpaExerciseEntity savedEntity = this.jpaExerciseRepository.save(jpaExerciseEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Exercise update(Exercise exercise) {
        JpaExerciseEntity jpaExerciseEntity = mapper.toJpaEntity(exercise);
        JpaExerciseEntity updatedEntity = this.jpaExerciseRepository.save(jpaExerciseEntity);
        return mapper.toDomain(updatedEntity);
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
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Exercise> findById(Long id) {
        return this.jpaExerciseRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Exercise> findByMuscleGroup(MuscleGroup muscleGroup) {
        return this.jpaExerciseRepository.findByMuscleGroup(muscleGroup)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Exercise> findByDifficulty(Difficulty difficulty) {
        return this.jpaExerciseRepository.findByDifficulty(difficulty)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Exercise> findByMuscleGroupAndDifficulty(MuscleGroup muscleGroup, Difficulty difficulty) {
        return this.jpaExerciseRepository.findByMuscleGroupAndDifficulty(muscleGroup, difficulty)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Exercise> findByNameContainingIgnoreCase(String name) {
        return this.jpaExerciseRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByName(String name) {
        return this.jpaExerciseRepository.existsByName(name);
    }
}
