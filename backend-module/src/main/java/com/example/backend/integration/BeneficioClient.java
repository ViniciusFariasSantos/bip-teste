package com.example.backend.integration;

import com.example.backend.dtos.BeneficioRecordDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class BeneficioClient {

    private final RestTemplate restTemplate = new RestTemplate();

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
        restTemplate.put(URL + "/" + id, dto);
    }

    public void deletar(Long id) {
        restTemplate.delete(URL + "/" + id);
    }

    public void transfer(Object transferDto) {
        restTemplate.postForObject(URL + "/transfer", transferDto, Void.class);
    }
}