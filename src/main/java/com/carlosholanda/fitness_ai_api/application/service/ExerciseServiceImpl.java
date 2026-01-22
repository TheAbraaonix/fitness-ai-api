package com.carlosholanda.fitness_ai_api.application.service;

import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.CreateExerciseRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.ExerciseResponse;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.UpdateExerciseRequest;
import com.carlosholanda.fitness_ai_api.application.usecases.ExerciseUseCases;
import com.carlosholanda.fitness_ai_api.domain.exercise.Difficulty;
import com.carlosholanda.fitness_ai_api.domain.exercise.Exercise;
import com.carlosholanda.fitness_ai_api.domain.exercise.ExerciseRepository;
import com.carlosholanda.fitness_ai_api.domain.exercise.MuscleGroup;
import com.carlosholanda.fitness_ai_api.infrastructure.exception.domain.exercise.ExerciseNotFoundException;
import com.carlosholanda.fitness_ai_api.utils.mapper.ExerciseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseUseCases {
    private final ExerciseRepository repository;
    private final ExerciseMapper mapper;

    public ExerciseServiceImpl(ExerciseRepository repository, ExerciseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ExerciseResponse create(CreateExerciseRequest request) {
        Exercise exercise = mapper.toEntity(request);
        Exercise savedExercise = repository.save(exercise);
        return mapper.toResponse(savedExercise);
    }

    @Override
    public ExerciseResponse update(Long id, UpdateExerciseRequest request) {
        Exercise existingExercise = repository.findById(id)
                .orElseThrow(() -> new ExerciseNotFoundException(id));

        mapper.updateEntityFromRequest(request, existingExercise);
        Exercise updatedExercise = repository.update(existingExercise);
        return mapper.toResponse(updatedExercise);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public ExerciseResponse getById(Long id) {
        Exercise exercise = repository.findById(id)
                .orElseThrow(() -> new ExerciseNotFoundException(id));
        return mapper.toResponse(exercise);
    }

    @Override
    public List<ExerciseResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<ExerciseResponse> getByMuscleGroup(MuscleGroup muscleGroup) {
        return repository.findByMuscleGroup(muscleGroup).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<ExerciseResponse> getByDifficulty(Difficulty difficulty) {
        return repository.findByDifficulty(difficulty).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<ExerciseResponse> getByMuscleGroupAndDifficulty(MuscleGroup muscleGroup, Difficulty difficulty) {
        return repository.findByMuscleGroupAndDifficulty(muscleGroup, difficulty).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<ExerciseResponse> searchByName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da busca não pode ser vazio");
        }

        return repository.findByNameContainingIgnoreCase(name).stream()
                .map(mapper::toResponse)
                .toList();
    }
}
