package com.carlosholanda.fitness_ai_api.utils.mapper;

import com.carlosholanda.fitness_ai_api.domain.user.User;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.user.CreateUserRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.user.UpdateUserRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.user.UserResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    // CreateUserRequest → User (Entity)
    User toEntity(CreateUserRequest request);
    
    // User (Entity) → UserResponse
    UserResponse toResponse(User user);
    
    // UpdateUserRequest → User (atualizar apenas campos não-nulos)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(UpdateUserRequest request, @MappingTarget User user);
}

