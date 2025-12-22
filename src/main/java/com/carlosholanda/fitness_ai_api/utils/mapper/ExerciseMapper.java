package com.carlosholanda.fitness_ai_api.utils.mapper;


import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.CreateExerciseRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.ExerciseResponse;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.UpdateExerciseRequest;
import com.carlosholanda.fitness_ai_api.domain.exercise.Exercise;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {
    
    // CreateExerciseRequest → Exercise (Entity)
    public Exercise toEntity(CreateExerciseRequest request) {
        Exercise exercise = new Exercise();
        exercise.setName(request.name());
        exercise.setMuscleGroup(request.muscleGroup());
        exercise.setInstructions(request.instructions());
        exercise.setDifficulty(request.difficulty());
        exercise.setEquipmentNeeded(request.equipmentNeeded());
        return exercise;
    }

    // UpdateExerciseRequest → Exercise (atualizar campos)
    public void updateEntityFromRequest(Exercise exercise, UpdateExerciseRequest request) {
        if (request.name() != null) {
            exercise.setName(request.name());
        }
        if (request.muscleGroup() != null) {
            exercise.setMuscleGroup(request.muscleGroup());
        }
        if (request.instructions() != null) {
            exercise.setInstructions(request.instructions());
        }
        if (request.difficulty() != null) {
            exercise.setDifficulty(request.difficulty());
        }
        if (request.equipmentNeeded() != null) {
            exercise.setEquipmentNeeded(request.equipmentNeeded());
        }
    }

    // Exercise (Entity) → ExerciseResponse
    public ExerciseResponse toResponse(Exercise exercise) {
        return new ExerciseResponse(
                exercise.getId(),
                exercise.getName(),
                exercise.getMuscleGroup(),
                exercise.getInstructions(),
                exercise.getDifficulty(),
                exercise.getEquipmentNeeded(),
                exercise.isActive(),
                exercise.getCreatedAt(),
                exercise.getUpdatedAt()
        );
    }
}
