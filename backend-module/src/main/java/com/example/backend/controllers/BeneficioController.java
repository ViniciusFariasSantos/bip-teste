package com.example.backend.controllers;

import com.example.backend.dtos.BeneficioRecordDto;
import com.example.backend.dtos.TransferDTO;
import com.example.backend.services.BeneficioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/beneficios")
public class BeneficioController {

    private final BeneficioService service;

    public BeneficioController(BeneficioService service) {
        this.service = service;
    }

    @GetMapping
    public List<BeneficioRecordDto> listar() {
        return service.listar();
    }

    @PostMapping
    public void salvar(@RequestBody BeneficioRecordDto dto) {
        service.salvar(dto);
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable Long id,
                          @RequestBody BeneficioRecordDto dto) {
        service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferDTO dto) {
        service.transfer(dto);
    }
}
