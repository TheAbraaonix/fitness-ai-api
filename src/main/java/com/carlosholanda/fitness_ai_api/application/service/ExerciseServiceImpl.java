package com.carlosholanda.fitness_ai_api.application.service;

import com.carlosholanda.fitness_ai_api.application.usecases.ExerciseUseCases;
import com.carlosholanda.fitness_ai_api.domain.exercise.Difficulty;
import com.carlosholanda.fitness_ai_api.domain.exercise.Exercise;
import com.carlosholanda.fitness_ai_api.domain.exercise.ExerciseRepository;
import com.carlosholanda.fitness_ai_api.domain.exercise.MuscleGroup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseUseCases {
    private final ExerciseRepository repository;

    public ExerciseServiceImpl(ExerciseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Exercise create(Exercise exercise) {
        return repository.save(exercise);
    }

    @Override
    public Exercise update(Long id, Exercise exercise) {
        Exercise existingExercise = getById(id);

        existingExercise.setName(exercise.getName());
        existingExercise.setMuscleGroup(exercise.getMuscleGroup());
        existingExercise.setInstructions(exercise.getInstructions());
        existingExercise.setDifficulty(exercise.getDifficulty());
        existingExercise.setEquipmentNeeded(exercise.getEquipmentNeeded());

        return repository.update(existingExercise);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Exercise getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercício não encontrado com o ID: " + id));
    }

    @Override
    public List<Exercise> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Exercise> getByMuscleGroup(MuscleGroup muscleGroup) {
        return repository.findByMuscleGroup(muscleGroup);
    }

    @Override
    public List<Exercise> getByDifficulty(Difficulty difficulty) {
        return repository.findByDifficulty(difficulty);
    }

    @Override
    public List<Exercise> getByMuscleGroupAndDifficulty(MuscleGroup muscleGroup, Difficulty difficulty) {
        return repository.findByMuscleGroupAndDifficulty(muscleGroup, difficulty);
    }

    @Override
    public List<Exercise> searchByName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new RuntimeException("Nome da busca não pode ser vazio");
        }

        return repository.findByNameContainingIgnoreCase(name);
    }
}
