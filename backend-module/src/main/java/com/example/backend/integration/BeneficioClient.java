package com.example.backend.integration;

import com.example.backend.dto.BeneficioRecordDto;
import com.example.backend.dto.TransferDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class BeneficioClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private final HttpHeaders headers = new HttpHeaders();

    private final String URL = "http://localhost:8080/ejb-module/api/beneficios";


    public List<BeneficioRecordDto> listar() {
        BeneficioRecordDto[] response =
                restTemplate.getForObject(URL, BeneficioRecordDto[].class);
        return Arrays.asList(response);
    }

    public void salvar(BeneficioRecordDto dto) {
        restTemplate.postForObject(URL, dto, Void.class);
    }

    public void atualizar(Long id, BeneficioRecordDto dto) {
        restTemplate.put(URL + "/" + id, dto, Void.class);
    }

    public void deletar(Long id) {
        try {

            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            restTemplate.exchange(
                    URL + "/" + id,
                    HttpMethod.DELETE,
                    entity,
                    Void.class
            );

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar benefício");
        }
    }

    public void transfer(TransferDTO transferDto) {
        restTemplate.postForObject(URL + "/transfer", transferDto, Void.class);
    }
}