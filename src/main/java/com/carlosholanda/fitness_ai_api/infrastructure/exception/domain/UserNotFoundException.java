package com.carlosholanda.fitness_ai_api.infrastructure.exception.domain;

public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException(String message) {
        super(message);
    }
    
    public UserNotFoundException(Long id) {
        super(String.format("User with ID %d not found", id));
    }
    
    public UserNotFoundException(String field, String value) {
        super(String.format("User with %s '%s' not found", field, value));
    }
}
