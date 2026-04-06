package com.example.backend.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransferDTOTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // ✅ TESTE GETTERS E SETTERS
    @Test
    void deveSetarEObterValoresCorretamente() {

        TransferDTO dto = new TransferDTO();

        dto.setFromId(1L);
        dto.setToId(2L);
        dto.setAmount(BigDecimal.valueOf(100));
        dto.setDescription("Transferência teste");

        assertEquals(1L, dto.getFromId());
        assertEquals(2L, dto.getToId());
        assertEquals(BigDecimal.valueOf(100), dto.getAmount());
        assertEquals("Transferência teste", dto.getDescription());
    }

    // ✅ TESTE SERIALIZAÇÃO JSON
    @Test
    void deveSerializarParaJsonCorretamente() throws Exception {

        TransferDTO dto = new TransferDTO();
        dto.setFromId(1L);
        dto.setToId(2L);
        dto.setAmount(BigDecimal.valueOf(100));
        dto.setDescription("Transferência teste");

        String json = objectMapper.writeValueAsString(dto);

        assertTrue(json.contains("\"fromId\":1"));
        assertTrue(json.contains("\"toId\":2"));
        assertTrue(json.contains("\"amount\":100"));
        assertTrue(json.contains("\"description\":\"Transferência teste\""));
    }

    // ✅ TESTE DESERIALIZAÇÃO JSON
    @Test
    void deveDeserializarJsonCorretamente() throws Exception {

        String json = """
        {
          "fromId": 1,
          "toId": 2,
          "amount": 100,
          "description": "Transferência teste"
        }
        """;

        TransferDTO dto = objectMapper.readValue(json, TransferDTO.class);

        assertEquals(1L, dto.getFromId());
        assertEquals(2L, dto.getToId());
        assertEquals(BigDecimal.valueOf(100), dto.getAmount());
        assertEquals("Transferência teste", dto.getDescription());
    }
}