package com.carlosholanda.fitness_ai_api.infrastructure.response;

/**
 * Constantes para códigos de resposta da API.
 * Usados para i18n no frontend - o frontend mapeia esses códigos para mensagens traduzidas.
 */
public final class ResponseCodes {

    private ResponseCodes() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // ==================== AUTH ====================
    public static final String AUTH_LOGIN_SUCCESS = "AUTH_LOGIN_SUCCESS";
    public static final String AUTH_REGISTER_SUCCESS = "AUTH_REGISTER_SUCCESS";
    
    // ==================== USER ====================
    public static final String USER_CREATED = "USER_CREATED";
    public static final String USER_UPDATED = "USER_UPDATED";
    public static final String USER_DELETED = "USER_DELETED";
    public static final String USER_RETRIEVED = "USER_RETRIEVED";
    public static final String USERS_RETRIEVED = "USERS_RETRIEVED";
    
    // ==================== EXERCISE ====================
    public static final String EXERCISE_CREATED = "EXERCISE_CREATED";
    public static final String EXERCISE_UPDATED = "EXERCISE_UPDATED";
    public static final String EXERCISE_DELETED = "EXERCISE_DELETED";
    public static final String EXERCISE_RETRIEVED = "EXERCISE_RETRIEVED";
    public static final String EXERCISES_RETRIEVED = "EXERCISES_RETRIEVED";
    
    // ==================== ERROR CODES ====================
    public static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
    public static final String USER_ALREADY_EXISTS = "USER_ALREADY_EXISTS";
    public static final String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";
    public static final String INVALID_ARGUMENT = "INVALID_ARGUMENT";
    public static final String VALIDATION_ERROR = "VALIDATION_ERROR";
    public static final String INTERNAL_ERROR = "INTERNAL_ERROR";
}
