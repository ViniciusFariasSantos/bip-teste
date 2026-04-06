package com.example.backend.controller;

import com.example.backend.dto.BeneficioRecordDto;
import com.example.backend.dto.TransferDTO;
import com.example.backend.service.BeneficioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/beneficios")
public class BeneficioController {

    private final BeneficioService service;

    public BeneficioController(BeneficioService service) {
        this.service = service;
    }

    @Operation(summary = "Listar benefícios")
    @GetMapping
    public List<BeneficioRecordDto> listar() {
        return service.listar();
    }

    @Operation(summary = "Salvar benefícios")
    @PostMapping
    public void salvar(@RequestBody BeneficioRecordDto dto) {
        service.salvar(dto);
    }

    @Operation(summary = "Atualizar benefícios")
    @PutMapping("/{id}")
    public void atualizar(@PathVariable Long id,
                          @RequestBody BeneficioRecordDto dto) {
        service.atualizar(id, dto);
    }

    @Operation(summary = "Deletar benefícios")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @Operation(summary = "Realizar transferência")
    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferDTO dto) {
        service.transfer(dto);
    }
}
