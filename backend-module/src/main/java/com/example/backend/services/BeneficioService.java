package com.example.backend.services;

import com.example.ejb.BeneficioEjbService;
import org.springframework.stereotype.Service;

import jakarta.ejb.EJB;
import java.math.BigDecimal;

@Service
public class BeneficioService {

    @EJB
    private BeneficioEjbService ejb;

    public void transferir(Long fromId, Long toId, BigDecimal amount) {
        ejb.transfer(fromId, toId, amount);
    }
}