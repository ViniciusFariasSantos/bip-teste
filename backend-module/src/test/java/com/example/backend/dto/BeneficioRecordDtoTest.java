package com.example.backend.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BeneficioRecordDtoTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // ✅ TESTE CRIAÇÃO DO RECORD
    @Test
    void deveCriarDtoCorretamente() {

        BeneficioRecordDto dto = new BeneficioRecordDto(
                1L,
                "VR",
                "Vale Refeição",
                BigDecimal.valueOf(500),
                true,
                "1"
        );

        assertEquals(1L, dto.id());
        assertEquals("VR", dto.name());
        assertEquals("Vale Refeição", dto.description());
        assertEquals(BigDecimal.valueOf(500), dto.value());
        assertTrue(dto.active());
        assertEquals("1", dto.version());
    }

    // ✅ TESTE SERIALIZAÇÃO JSON
    @Test
    void deveSerializarParaJsonCorretamente() throws Exception {

        BeneficioRecordDto dto = new BeneficioRecordDto(
                1L,
                "VR",
                "Vale Refeição",
                BigDecimal.valueOf(500),
                true,
                "1"
        );

        String json = objectMapper.writeValueAsString(dto);

        assertTrue(json.contains("\"nome\":\"VR\""));
        assertTrue(json.contains("\"descricao\":\"Vale Refeição\""));
        assertTrue(json.contains("\"valor\":500"));
        assertTrue(json.contains("\"ativo\":true"));
    }

    // ✅ TESTE DESERIALIZAÇÃO JSON
    @Test
    void deveDeserializarJsonCorretamente() throws Exception {

        String json = """
        {
          "id": 1,
          "nome": "VR",
          "descricao": "Vale Refeição",
          "valor": 500,
          "ativo": true,
          "version": "1"
        }
        """;

        BeneficioRecordDto dto = objectMapper.readValue(json, BeneficioRecordDto.class);

        assertEquals(1L, dto.id());
        assertEquals("VR", dto.name());
        assertEquals("Vale Refeição", dto.description());
        assertEquals(BigDecimal.valueOf(500), dto.value());
        assertTrue(dto.active());
        assertEquals("1", dto.version());
    }
}