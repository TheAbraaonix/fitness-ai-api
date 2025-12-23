package com.carlosholanda.fitness_ai_api.adapters.inbound.controller;

import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.CreateExerciseRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.ExerciseResponse;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.UpdateExerciseRequest;
import com.carlosholanda.fitness_ai_api.application.usecases.ExerciseUseCases;
import com.carlosholanda.fitness_ai_api.domain.exercise.Difficulty;
import com.carlosholanda.fitness_ai_api.domain.exercise.Exercise;
import com.carlosholanda.fitness_ai_api.domain.exercise.MuscleGroup;
import com.carlosholanda.fitness_ai_api.utils.mapper.ExerciseMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {
    private final ExerciseUseCases exerciseUseCases;
    private final ExerciseMapper mapper;

    public ExerciseController(ExerciseUseCases exerciseUseCases, ExerciseMapper mapper) {
        this.exerciseUseCases = exerciseUseCases;
        this.mapper = mapper;
    }

    // POST /api/exercises - Criar exercício
    @PostMapping
    public ResponseEntity<ExerciseResponse> create(@Valid @RequestBody CreateExerciseRequest request) {
        Exercise exercise = mapper.toEntity(request);
        Exercise savedExercise = exerciseUseCases.create(exercise);
        ExerciseResponse response = mapper.toResponse(savedExercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET /api/exercises - Buscar todos
    @GetMapping
    public ResponseEntity<List<ExerciseResponse>> getAll() {
        List<Exercise> exercises = exerciseUseCases.getAll();
        List<ExerciseResponse> responses = exercises.stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    // GET /api/exercises/{id} - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseResponse> getById(@PathVariable Long id) {
        Exercise exercise = exerciseUseCases.getById(id);
        ExerciseResponse response = mapper.toResponse(exercise);
        return ResponseEntity.ok(response);
    }

    // GET /api/exercises/muscle-group/{muscleGroup} - Buscar por grupo muscular
    @GetMapping("/muscle-group/{muscleGroup}")
    public ResponseEntity<List<ExerciseResponse>> getByMuscleGroup(@PathVariable MuscleGroup muscleGroup) {
        List<Exercise> exercises = exerciseUseCases.getByMuscleGroup(muscleGroup);
        List<ExerciseResponse> responses = exercises.stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    // GET /api/exercises/difficulty/{difficulty} - Buscar por dificuldade
    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<ExerciseResponse>> getByDifficulty(@PathVariable Difficulty difficulty) {
        List<Exercise> exercises = exerciseUseCases.getByDifficulty(difficulty);
        List<ExerciseResponse> responses = exercises.stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    // GET /api/exercises/filter?muscleGroup=CHEST&difficulty=BEGINNER
    @GetMapping("/filter")
    public ResponseEntity<List<ExerciseResponse>> getByMuscleGroupAndDifficulty(
            @RequestParam MuscleGroup muscleGroup,
            @RequestParam Difficulty difficulty
    ) {
        List<Exercise> exercises = exerciseUseCases.getByMuscleGroupAndDifficulty(muscleGroup, difficulty);
        List<ExerciseResponse> responses = exercises.stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    // GET /api/exercises/search?name=supino
    @GetMapping("/search")
    public ResponseEntity<List<ExerciseResponse>> searchByName(@RequestParam String name) {
        List<Exercise> exercises = exerciseUseCases.searchByName(name);
        List<ExerciseResponse> responses = exercises.stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    // PUT /api/exercises/{id} - Atualizar exercício
    @PutMapping("/{id}")
    public ResponseEntity<ExerciseResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateExerciseRequest request
    ) {
        Exercise exercise = exerciseUseCases.getById(id);
        mapper.updateEntityFromRequest(exercise, request);
        Exercise updatedExercise = exerciseUseCases.update(id, exercise);
        ExerciseResponse response = mapper.toResponse(updatedExercise);
        return ResponseEntity.ok(response);
    }

    // DELETE /api/exercises/{id} - Deletar (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exerciseUseCases.delete(id);
        return ResponseEntity.noContent().build();
    }
}
