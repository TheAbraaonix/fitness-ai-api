package com.carlosholanda.fitness_ai_api.adapters.inboud.dto.userDto;

import com.carlosholanda.fitness_ai_api.domain.user.FitnessGoal;
import com.carlosholanda.fitness_ai_api.domain.user.FitnessLevel;
import jakarta.validation.constraints.*;

public record CreateUserRequest(
        @NotNull @NotBlank String name,
        @NotNull @NotBlank @Email String email,
        @NotNull @NotBlank @Size(min = 6) String password,

        @Min(14) @Max(100) Integer age,
        @DecimalMin("30.0") @DecimalMax("300.0") Double weight,
        @DecimalMin("1.0") @DecimalMax("2.5") Double height,
        @Min(1) @Max(7) Integer availableDaysPerWeek,
        FitnessGoal fitnessGoal,
        FitnessLevel fitnessLevel
) {
}
