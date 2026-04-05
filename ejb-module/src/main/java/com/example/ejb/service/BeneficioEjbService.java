package com.example.ejb.service;

import com.example.ejb.dto.BeneficioDTO;
import com.example.ejb.dto.ErrorDTO;
import com.example.ejb.entity.Beneficio;
import com.example.ejb.repository.BeneficioRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;
import javax.ws.rs.WebApplicationException;
import java.math.BigDecimal;
import java.util.List;

@Stateless
public class BeneficioEjbService {

    @EJB
    private BeneficioRepository repository;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void transfer(Long fromId, Long toId, BigDecimal amount) {

        Beneficio from = repository.buscarPorIdComLock(fromId);
        Beneficio to   = repository.buscarPorIdComLock(toId);

        if (from == null || to == null) {
            throw new WebApplicationException(
                    javax.ws.rs.core.Response.status(404)
                            .entity(new ErrorDTO("Conta não encontrada"))
                            .type(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                            .build()
            );
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new WebApplicationException(
                    javax.ws.rs.core.Response.status(400)
                            .entity(new ErrorDTO("Valor inválido"))
                            .type(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                            .build()
            );
        }

        if (from.getValor().compareTo(amount) < 0) {
            throw new WebApplicationException(
                    javax.ws.rs.core.Response.status(400)
                            .entity(new ErrorDTO("Saldo insuficiente"))
                            .type(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                            .build()
            );
        }

        // regra de negócio
        from.setValor(from.getValor().subtract(amount));
        to.setValor(to.getValor().add(amount));

        // persistência delegada
        repository.atualizar(from);
        repository.atualizar(to);
    }

    public List<Beneficio> listar() {
        return repository.listar();
    }

    public void salvar(BeneficioDTO dto) {
        Beneficio beneficio = new Beneficio();

        beneficio.setNome(dto.getNome());
        beneficio.setDescricao(dto.getDescricao());
        beneficio.setValor(dto.getValor());
        beneficio.setAtivo(dto.getAtivo());

        repository.salvar(beneficio);
    }

    public void atualizar(Long id,BeneficioDTO dto) {
        Beneficio beneficio = repository.buscarPorId(id);

        if (beneficio == null) {
            throw new IllegalArgumentException("Benefício não encontrado");
        }

        beneficio.setNome(dto.getNome());
        beneficio.setDescricao(dto.getDescricao());
        beneficio.setValor(dto.getValor());
        beneficio.setAtivo(dto.getAtivo());

        repository.atualizar(beneficio);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new javax.ws.rs.WebApplicationException("Benefício não encontrado", 404);
        }
        repository.deletar(id);
    }

}