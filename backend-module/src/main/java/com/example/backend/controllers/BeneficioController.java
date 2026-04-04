package com.example.backend.controllers;

import com.example.backend.dtos.BeneficioRecordDto;
import com.example.backend.models.BeneficioModel;
import com.example.backend.repositories.BeneficioRepository;
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
@RequestMapping("/api/v1/beneficios")
public class BeneficioController {

    @Autowired
    BeneficioRepository beneficioRepository;

    @PostMapping
    public ResponseEntity<BeneficioModel> saveBeneficio(@RequestBody @Valid BeneficioRecordDto beneficioRecordDto){
        var beneficioModel = new BeneficioModel();
        BeanUtils.copyProperties(beneficioRecordDto, beneficioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(beneficioRepository.save(beneficioModel));
    }

    @GetMapping
    public ResponseEntity<List<BeneficioModel>> getAllBeneficios(){
        return ResponseEntity.status(HttpStatus.OK).body(beneficioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneBeneficio(@PathVariable(value="id") UUID id){
        Optional<BeneficioModel> beneficioO = beneficioRepository.findById(id);
        if(beneficioO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(beneficioO.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(beneficioO.get());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object>
    deleteBeneficio(@PathVariable(value="id") UUID id) {
        Optional<BeneficioModel> beneficioO =
                beneficioRepository.findById(id);
        if(beneficioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Beneficio not found.");
        }
        beneficioRepository.delete(beneficioO.get());
        return ResponseEntity.status(HttpStatus.OK)
                .body("Beneficio deleted successfully.");
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object>
    updateBeneficio(@PathVariable(value="id") UUID id,
                  @RequestBody @Valid BeneficioRecordDto beneficioRecordDto) {
        Optional<BeneficioModel> beneficioO =
                beneficioRepository.findById(id);
        if(beneficioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Beneficio not found.");
        }
        var beneficioModel = beneficioO.get();
        BeanUtils.copyProperties(beneficioRecordDto,
                beneficioModel);
        return ResponseEntity.status(HttpStatus.OK)
                .body(beneficioRepository.save(beneficioModel));
    }

    
}
