package com.carlosholanda.fitness_ai_api.infrastructure.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Wrapper padrão para todas as respostas de sucesso da API.
 * Garante formato consistente em todos os endpoints.
 * 
 * @param <T> Tipo dos dados retornados (UserResponse, ExerciseResponse, etc.)
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)  // Não serializa campos nulos
public class ApiResponse<T> {
    
    private final boolean success;
    private final T data;
    private final String message;
    private final LocalDateTime timestamp;
    
    /**
     * Construtor privado - use os métodos estáticos para criar instâncias
     */
    private ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
    
    /**
     * Cria resposta de sucesso com dados e mensagem
     * 
     * @param data Dados a serem retornados
     * @param message Mensagem de sucesso
     * @return ApiResponse com success=true
     */
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, data, message);
    }
    
    /**
     * Cria resposta de sucesso apenas com dados (sem mensagem)
     * 
     * @param data Dados a serem retornados
     * @return ApiResponse com success=true
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }
    
    /**
     * Cria resposta de sucesso apenas com mensagem (sem dados)
     * Útil para operações como DELETE que não retornam dados
     * 
     * @param message Mensagem de sucesso
     * @return ApiResponse com success=true e data=null
     */
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, null, message);
    }
}
