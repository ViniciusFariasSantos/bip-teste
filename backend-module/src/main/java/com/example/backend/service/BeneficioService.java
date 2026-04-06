package com.example.backend.service;

import com.example.backend.dto.BeneficioRecordDto;
import com.example.backend.dto.TransferDTO;
import com.example.backend.integration.BeneficioClient;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BeneficioService {

    private final BeneficioClient client;

    public BeneficioService(BeneficioClient client) {
        this.client = client;
    }

    public List<BeneficioRecordDto> listar() {
        return client.listar();
    }

    public void salvar(BeneficioRecordDto dto) {
        client.salvar(dto);
    }

    public void atualizar(Long id, BeneficioRecordDto dto) {
        client.atualizar(id, dto);
    }

    public void deletar(Long id) {
        client.deletar(id);
    }

    public void transfer(TransferDTO dto) {
        client.transfer(dto);
    }
}