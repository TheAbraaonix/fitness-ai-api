package com.carlosholanda.fitness_ai_api.infrastructure.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Standard wrapper for all API error responses.
 * Returned by GlobalExceptionHandler with appropriate HTTP status.
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    
    private final boolean success = false;  // Always false for errors
    private final ErrorDetails error;
    private final LocalDateTime timestamp;
    
    /**
     * Private constructor - use static methods
     */
    private ApiError(String code, String message, List<ValidationError> details) {
        this.error = new ErrorDetails(code, message, details);
        this.timestamp = LocalDateTime.now();
    }
    
    /**
     * Creates error with code, message and validation details
     * 
     * @param code Error code (e.g., "EXERCISE_NOT_FOUND")
     * @param message Descriptive error message
     * @param details List of validation errors (invalid fields)
     * @return ApiError
     */
    public static ApiError of(String code, String message, List<ValidationError> details) {
        return new ApiError(code, message, details);
    }
    
    /**
     * Creates simple error without validation details
     * 
     * @param code Error code (e.g., "USER_NOT_FOUND")
     * @param message Descriptive error message
     * @return ApiError
     */
    public static ApiError of(String code, String message) {
        return new ApiError(code, message, null);
    }
    
    /**
     * Internal error details
     */
    @Getter
    public static class ErrorDetails {
        private final String code;
        private final String message;
        
        @JsonInclude(JsonInclude.Include.NON_EMPTY)  // Only appears if there are items
        private final List<ValidationError> details;
        
        public ErrorDetails(String code, String message, List<ValidationError> details) {
            this.code = code;
            this.message = message;
            this.details = details;
        }
    }
    
    /**
     * Represents a validation error on a specific field
     */
    @Getter
    public static class ValidationError {
        private final String field;
        private final String message;
        private final Object rejectedValue;
        
        public ValidationError(String field, String message, Object rejectedValue) {
            this.field = field;
            this.message = message;
            this.rejectedValue = rejectedValue;
        }
        
        public ValidationError(String field, String message) {
            this(field, message, null);
        }
    }
}
