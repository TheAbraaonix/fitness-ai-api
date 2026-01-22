package com.carlosholanda.fitness_ai_api.utils.mapper;

import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout.CreateWorkoutRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout.UpdateWorkoutRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.workout.WorkoutResponse;
import com.carlosholanda.fitness_ai_api.domain.workout.Workout;
import com.carlosholanda.fitness_ai_api.adapters.outbound.entities.JpaWorkoutEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {WorkoutExerciseMapper.class})
public interface WorkoutMapper {
    
    // ========== DTOs ↔ Domain ==========
    
    // CreateWorkoutRequest → Workout (Domain)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true) // Will be set automatically based on exercises
    @Mapping(target = "aiGenerated", constant = "false") // Manual creation
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Workout toEntity(CreateWorkoutRequest request);
    
    // Workout (Domain) → WorkoutResponse
    WorkoutResponse toResponse(Workout workout);
    
    // UpdateWorkoutRequest → Workout (update only non-null fields)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "aiGenerated", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequest(UpdateWorkoutRequest request, @MappingTarget Workout workout);
    
    // ========== Domain ↔ JPA ==========
    
    // Workout (Domain) → JpaWorkoutEntity (Persistence)
    @Mapping(target = "user", ignore = true) // Set manually in service layer
    @Mapping(target = "createdAt", ignore = true) // Managed by @PrePersist
    @Mapping(target = "updatedAt", ignore = true) // Managed by @PreUpdate
    JpaWorkoutEntity toJpaEntity(Workout workout);
    
    // JpaWorkoutEntity (Persistence) → Workout (Domain)
    @Mapping(source = "user.id", target = "userId")
    Workout toDomain(JpaWorkoutEntity entity);
}
