package com.example.backend.integration;

import com.example.backend.dto.BeneficioRecordDto;
import com.example.backend.dto.TransferDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

class BeneficioClientTest {

    private BeneficioClient client;
    private MockRestServiceServer mockServer;

    private final String URL = "http://localhost:8080/ejb-module/api/beneficios";

    @BeforeEach
    void setup() {
        RestTemplate restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);

        client = new BeneficioClient(restTemplate);
    }

    // ✅ TESTE LISTAR
    @Test
    void deveListarBeneficios() {

        String json = """
            [
              {
                "id": 1,
                "nome": "VR",
                "descricao": "Vale Refeição",
                "valor": 500,
                "ativo": true,
                "version": "1"
              }
            ]
        """;

        mockServer.expect(requestTo(URL))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        List<BeneficioRecordDto> lista = client.listar();

        assertEquals(1, lista.size());
        assertEquals("VR", lista.get(0).name());
    }

    // ✅ TESTE SALVAR
    @Test
    void deveSalvarBeneficio() {

        mockServer.expect(requestTo(URL))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        BeneficioRecordDto dto = new BeneficioRecordDto(
                null, "VR", "Vale Refeição",
                BigDecimal.valueOf(500), true, "1"
        );

        client.salvar(dto);

        mockServer.verify();
    }

    // ✅ TESTE ATUALIZAR
    @Test
    void deveAtualizarBeneficio() {

        mockServer.expect(requestTo(URL + "/1"))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withSuccess());

        BeneficioRecordDto dto = new BeneficioRecordDto(
                null, "VR", "Atualizado",
                BigDecimal.valueOf(600), true, "1"
        );

        client.atualizar(1L, dto);

        mockServer.verify();
    }

    // ✅ TESTE DELETE
    @Test
    void deveDeletarBeneficio() {

        mockServer.expect(requestTo(URL + "/1"))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withSuccess());

        client.deletar(1L);

        mockServer.verify();
    }

    // 🔥 TESTE ERRO DELETE
    @Test
    void deveLancarErroAoDeletar() {

        mockServer.expect(requestTo(URL + "/1"))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withServerError());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            client.deletar(1L);
        });

        assertEquals("Erro ao deletar benefício", ex.getMessage());
    }

    // 🔥 TESTE TRANSFER
    @Test
    void deveRealizarTransferencia() {

        mockServer.expect(requestTo(URL + "/transfer"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        TransferDTO dto = new TransferDTO();
        dto.setAmount(BigDecimal.valueOf(100));

        client.transfer(dto);

        mockServer.verify();
    }
}