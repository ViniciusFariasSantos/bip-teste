package com.example.ejb.repository;

import com.example.ejb.entity.Beneficio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BeneficioRepository {

    @PersistenceContext(unitName = "MeuPU")
    private EntityManager em;

    public Beneficio buscarPorIdComLock(Long id) {
        return em.find(Beneficio.class, id, LockModeType.PESSIMISTIC_WRITE);
    }

    public void atualizar(Beneficio b) {
        em.merge(b);
    }

    public Beneficio buscarPorId(Long id) {
        return em.find(Beneficio.class, id);
    }

    public List<Beneficio> listar() {
        return em.createQuery("SELECT b FROM Beneficio b", Beneficio.class)
                .getResultList();
    }

    public void salvar(Beneficio beneficio) {
        em.persist(beneficio);
    }

    public void deletar(Long id) {
        Beneficio beneficio = em.find(Beneficio.class, id);
        em.remove(beneficio);
    }

}

