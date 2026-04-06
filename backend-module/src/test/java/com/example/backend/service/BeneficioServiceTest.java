package com.example.backend.service;



import com.example.backend.dto.BeneficioRecordDto;
import com.example.backend.dto.TransferDTO;
import com.example.backend.integration.BeneficioClient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BeneficioServiceTest {

    @Mock
    private BeneficioClient client;

    @InjectMocks
    private BeneficioService service;

    @Test
    void deveListarBeneficios() {

        BeneficioRecordDto dto = new BeneficioRecordDto(
                1L,
                "VR",
                "Vale Refeição",
                BigDecimal.valueOf(500),
                true,
                "1"
        );

        Mockito.when(client.listar()).thenReturn(List.of(dto));

        List<BeneficioRecordDto> resultado = service.listar();

        assertEquals(1, resultado.size());
        assertEquals("VR", resultado.get(0).name());

        Mockito.verify(client).listar();
    }

    @Test
    void deveSalvarBeneficio() {

        BeneficioRecordDto dto = new BeneficioRecordDto(
                null,
                "VR",
                "Vale Refeição",
                BigDecimal.valueOf(500),
                true,
                "1"
        );

        service.salvar(dto);

        Mockito.verify(client).salvar(dto);
    }

    @Test
    void deveAtualizarBeneficio() {

        BeneficioRecordDto dto = new BeneficioRecordDto(
                null,
                "VR",
                "Atualizado",
                BigDecimal.valueOf(600),
                true,
                "1"
        );

        service.atualizar(1L, dto);

        Mockito.verify(client).atualizar(1L, dto);
    }

    @Test
    void deveDeletarBeneficio() {

        service.deletar(1L);

        Mockito.verify(client).deletar(1L);
    }
    @Test
    void deveRealizarTransferencia() {

        TransferDTO dto = new TransferDTO();
        dto.setAmount(BigDecimal.valueOf(100));

        service.transfer(dto);

        Mockito.verify(client).transfer(dto);
    }

    @Test
    void deveLancarExcecaoQuandoClientFalhar() {

        TransferDTO dto = new TransferDTO();
        dto.setAmount(BigDecimal.valueOf(100));

        Mockito.doThrow(new RuntimeException("Erro na transferência"))
                .when(client).transfer(Mockito.any());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.transfer(dto);
        });

        assertEquals("Erro na transferência", exception.getMessage());
    }
}