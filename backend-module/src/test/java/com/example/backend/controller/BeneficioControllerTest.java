package com.example.backend.controller;

import com.example.backend.dto.BeneficioRecordDto;
import com.example.backend.dto.TransferDTO;
import com.example.backend.service.BeneficioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeneficioController.class)
class BeneficioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeneficioService service;

    @Autowired
    private ObjectMapper objectMapper;

    // ✅ GET
    @Test
    void deveListarBeneficios() throws Exception {

        BeneficioRecordDto dto = new BeneficioRecordDto(
                1L,
                "VR",
                "Vale Refeição",
                BigDecimal.valueOf(500),
                true,
                "1"
        );

        Mockito.when(service.listar()).thenReturn(List.of(dto));

        mockMvc.perform(get("/beneficios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("VR")); // 🔥 usa "nome"
    }

    // ✅ POST
    @Test
    void deveSalvarBeneficio() throws Exception {

        BeneficioRecordDto dto = new BeneficioRecordDto(
                null,
                "VR",
                "Vale Refeição",
                BigDecimal.valueOf(500),
                true,
                "1"
        );

        mockMvc.perform(post("/beneficios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        Mockito.verify(service).salvar(Mockito.any());
    }

    // ✅ PUT
    @Test
    void deveAtualizarBeneficio() throws Exception {

        BeneficioRecordDto dto = new BeneficioRecordDto(
                null,
                "VR",
                "Atualizado",
                BigDecimal.valueOf(600),
                true,
                "1"
        );

        mockMvc.perform(put("/beneficios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        Mockito.verify(service).atualizar(Mockito.eq(1L), Mockito.any());
    }

    // ✅ DELETE
    @Test
    void deveDeletarBeneficio() throws Exception {

        mockMvc.perform(delete("/beneficios/1"))
                .andExpect(status().isOk());

        Mockito.verify(service).deletar(1L);
    }

    // 🔥 TRANSFER
    @Test
    void deveRealizarTransferencia() throws Exception {

        TransferDTO dto = new TransferDTO();
        dto.setAmount(BigDecimal.valueOf(100));

        mockMvc.perform(post("/beneficios/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        Mockito.verify(service).transfer(Mockito.any());
    }
}