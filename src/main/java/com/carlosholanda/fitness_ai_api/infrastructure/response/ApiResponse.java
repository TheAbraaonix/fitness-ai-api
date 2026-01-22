package com.carlosholanda.fitness_ai_api.infrastructure.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Standard wrapper for all successful API responses.
 * Ensures consistent format across all endpoints.
 * 
 * @param <T> Type of returned data (UserResponse, ExerciseResponse, etc.)
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)  // Don't serialize null fields
public class ApiResponse<T> {
    
    private final boolean success;
    private final String code;
    private final T data;
    private final String message;
    private final LocalDateTime timestamp;
    
    /**
     * Private constructor - use static methods to create instances
     */
    private ApiResponse(boolean success, String code, T data, String message) {
        this.success = success;
        this.code = code;
        this.data = data;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
    
    /**
     * Creates success response with code, data and message
     * 
     * @param code Operation code (e.g., "AUTH_LOGIN_SUCCESS")
     * @param data Data to be returned
     * @param message Success message in English (fallback)
     * @return ApiResponse with success=true
     */
    public static <T> ApiResponse<T> success(String code, T data, String message) {
        return new ApiResponse<>(true, code, data, message);
    }
    
    /**
     * Creates success response with data and message (without code)
     * 
     * @param data Data to be returned
     * @param message Success message
     * @return ApiResponse with success=true
     */
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, null, data, message);
    }
    
    /**
     * Creates success response with data only (no message)
     * 
     * @param data Data to be returned
     * @return ApiResponse with success=true
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, null, data, null);
    }

    /**
     * Creates success response with code and message only (no data)
     * @param code Operation code (e.g., "AUTH_LOGIN_SUCCESS")
     * @param message Success message
     * @return
     */
    public static <T> ApiResponse<T> success(String code, String message) {
        return new ApiResponse<>(true, code, null, message);
    }
    
    /**
     * Creates success response with message only (no data)
     * Useful for operations like DELETE that don't return data
     * 
     * @param message Success message
     * @return ApiResponse with success=true and data=null
     */
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, null, null, message);
    }
}
