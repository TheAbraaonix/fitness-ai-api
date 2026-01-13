package com.carlosholanda.fitness_ai_api.utils.mapper;

import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.CreateExerciseRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.ExerciseResponse;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.exercise.UpdateExerciseRequest;
import com.carlosholanda.fitness_ai_api.domain.exercise.Exercise;
import com.carlosholanda.fitness_ai_api.adapters.outbound.entities.JpaExerciseEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {
    
    // ========== DTOs ↔ Domain ==========
    
    // CreateExerciseRequest → Exercise (Entity)
    Exercise toEntity(CreateExerciseRequest request);
    
    // Exercise (Entity) → ExerciseResponse
    ExerciseResponse toResponse(Exercise exercise);
    
    // UpdateExerciseRequest → Exercise (atualizar apenas campos não-nulos)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(UpdateExerciseRequest request, @MappingTarget Exercise exercise);
    
    // ========== Domain ↔ JPA ==========
    
    // Exercise (Domain) → JpaExerciseEntity (Persistence)
    JpaExerciseEntity toJpaEntity(Exercise exercise);
    
    // JpaExerciseEntity (Persistence) → Exercise (Domain)
    Exercise toDomain(JpaExerciseEntity entity);
}
