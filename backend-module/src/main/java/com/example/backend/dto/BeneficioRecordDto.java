package com.example.backend.dto;

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
        Boolean active,

        @JsonProperty("version")
        String version

) {}