package com.carlosholanda.fitness_ai_api.adapters.inbound.controller;

import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.CreateExerciseRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.ExerciseResponse;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.UpdateExerciseRequest;
import com.carlosholanda.fitness_ai_api.application.usecases.ExerciseUseCases;
import com.carlosholanda.fitness_ai_api.domain.exercise.Difficulty;
import com.carlosholanda.fitness_ai_api.domain.exercise.MuscleGroup;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {
    private final ExerciseUseCases exerciseUseCases;

    public ExerciseController(ExerciseUseCases exerciseUseCases) {
        this.exerciseUseCases = exerciseUseCases;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExerciseResponse> create(@Valid @RequestBody CreateExerciseRequest request) {
        ExerciseResponse response = exerciseUseCases.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ExerciseResponse>> getAll() {
        List<ExerciseResponse> responses = exerciseUseCases.getAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseResponse> getById(@PathVariable Long id) {
        ExerciseResponse response = exerciseUseCases.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/muscle-group/{muscleGroup}")
    public ResponseEntity<List<ExerciseResponse>> getByMuscleGroup(@PathVariable MuscleGroup muscleGroup) {
        List<ExerciseResponse> responses = exerciseUseCases.getByMuscleGroup(muscleGroup);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<ExerciseResponse>> getByDifficulty(@PathVariable Difficulty difficulty) {
        List<ExerciseResponse> responses = exerciseUseCases.getByDifficulty(difficulty);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ExerciseResponse>> getByMuscleGroupAndDifficulty(
            @RequestParam MuscleGroup muscleGroup,
            @RequestParam Difficulty difficulty
    ) {
        List<ExerciseResponse> responses = exerciseUseCases.getByMuscleGroupAndDifficulty(muscleGroup, difficulty);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ExerciseResponse>> searchByName(@RequestParam String name) {
        List<ExerciseResponse> responses = exerciseUseCases.searchByName(name);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExerciseResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateExerciseRequest request
    ) {
        ExerciseResponse response = exerciseUseCases.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exerciseUseCases.delete(id);
        return ResponseEntity.noContent().build();
    }
}
