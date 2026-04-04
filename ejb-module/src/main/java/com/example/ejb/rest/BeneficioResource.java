package com.example.ejb.rest;

import com.example.ejb.dto.BeneficioDTO;
import com.example.ejb.dto.TransferDTO;
import com.example.ejb.entity.Beneficio;
import com.example.ejb.service.BeneficioEjbService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/beneficios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BeneficioResource {

    @EJB
    private BeneficioEjbService service;

    @GET
    public List<Beneficio> listar() {
        return service.listar();
    }

    @POST
    public void salvar(BeneficioDTO dto) {
        service.salvar(dto);
    }

    @Path("/transfer")
    @POST
    public void transfer(TransferDTO dto) {
        service.transfer(dto.getFromId(), dto.getToId(), dto.getAmount());
    }

    @POST
    @Path("/{id}")
    public void atualizar(@PathParam("id") Long id, BeneficioDTO dto) {
        service.atualizar(dto, id);
    }

    @DELETE
    @Path("/{id}")
    public void deletar(@PathParam("id") Long id) {
        service.deletar(id);
    }
}
