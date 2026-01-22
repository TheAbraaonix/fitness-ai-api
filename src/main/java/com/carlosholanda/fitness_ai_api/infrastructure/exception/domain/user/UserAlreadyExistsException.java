package com.carlosholanda.fitness_ai_api.infrastructure.exception.domain.user;

public class UserAlreadyExistsException extends RuntimeException {
    
    public UserAlreadyExistsException(String email) {
        super(String.format("User with email '%s' already exists", email));
    }
}
