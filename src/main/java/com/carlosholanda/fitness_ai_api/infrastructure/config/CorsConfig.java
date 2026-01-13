package com.carlosholanda.fitness_ai_api.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

  @Bean
  public CorsFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration();

    // Permite credenciais (cookies, headers de autenticação)
    config.setAllowCredentials(true);

    // Origens permitidas (ajuste conforme necessário)
    config.setAllowedOrigins(Arrays.asList(
            "http://localhost:4200"
    ));

    // Headers permitidos
    config.setAllowedHeaders(Arrays.asList(
            "Origin",
            "Content-Type",
            "Accept",
            "Authorization",
            "Access-Control-Request-Method",
            "Access-Control-Request-Headers"
    ));

    // Métodos HTTP permitidos
    config.setAllowedMethods(Arrays.asList(
            "GET",
            "POST",
            "PUT",
            "DELETE",
            "PATCH",
            "OPTIONS"
    ));

    // Headers expostos ao frontend
    config.setExposedHeaders(Arrays.asList(
            "Authorization",
            "Content-Type"
    ));

    // Tempo de cache da configuração CORS (em segundos)
    config.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);

    return new CorsFilter(source);
  }
}
