package com.example.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;

public record BeneficioRecordDto(

        Long id,

        @JsonProperty("nome")
        String name,

        @JsonProperty("descricao")
        String description,

        @JsonProperty("valor")
        BigDecimal value,

        @JsonProperty("ativo")
        Boolean active

) {}