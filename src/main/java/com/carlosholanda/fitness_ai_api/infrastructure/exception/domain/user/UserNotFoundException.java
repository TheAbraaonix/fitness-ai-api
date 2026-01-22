package com.carlosholanda.fitness_ai_api.infrastructure.exception.domain.user;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException(String message) {
        super(message);
    }
    
    public UserNotFoundException(UUID id) {
        super(String.format("User with ID %d not found", id));
    }
    
    public UserNotFoundException(String field, String value) {
        super(String.format("User with %s '%s' not found", field, value));
    }
}
