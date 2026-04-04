package com.example.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record BeneficioRecordDto(@NotBlank String name, @NotNull BigDecimal value) {
}
