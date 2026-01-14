package com.carlosholanda.fitness_ai_api.infrastructure.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Wrapper padrão para todas as respostas de erro da API.
 * Retornado pelo GlobalExceptionHandler com status HTTP apropriado.
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    
    private final boolean success = false;  // Sempre false em erros
    private final ErrorDetails error;
    private final LocalDateTime timestamp;
    
    /**
     * Construtor privado - use os métodos estáticos
     */
    private ApiError(String code, String message, List<ValidationError> details) {
        this.error = new ErrorDetails(code, message, details);
        this.timestamp = LocalDateTime.now();
    }
    
    /**
     * Cria erro com código, mensagem e detalhes de validação
     * 
     * @param code Código do erro (ex: "EXERCISE_NOT_FOUND")
     * @param message Mensagem descritiva do erro
     * @param details Lista de erros de validação (campos inválidos)
     * @return ApiError
     */
    public static ApiError of(String code, String message, List<ValidationError> details) {
        return new ApiError(code, message, details);
    }
    
    /**
     * Cria erro simples sem detalhes de validação
     * 
     * @param code Código do erro (ex: "USER_NOT_FOUND")
     * @param message Mensagem descritiva do erro
     * @return ApiError
     */
    public static ApiError of(String code, String message) {
        return new ApiError(code, message, null);
    }
    
    /**
     * Detalhes internos do erro
     */
    @Getter
    public static class ErrorDetails {
        private final String code;
        private final String message;
        
        @JsonInclude(JsonInclude.Include.NON_EMPTY)  // Só aparece se tiver itens
        private final List<ValidationError> details;
        
        public ErrorDetails(String code, String message, List<ValidationError> details) {
            this.code = code;
            this.message = message;
            this.details = details;
        }
    }
    
    /**
     * Representa um erro de validação em um campo específico
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
