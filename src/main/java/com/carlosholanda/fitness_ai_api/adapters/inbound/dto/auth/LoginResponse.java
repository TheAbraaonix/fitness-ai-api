package com.carlosholanda.fitness_ai_api.adapters.inbound.dto.auth;

import java.util.UUID;

public record LoginResponse(
  String token,
  String type,
  UUID userId,
  String name,
  String email,
  String role
) {

}
