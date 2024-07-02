package com.killa.sierravp.service;

import com.killa.sierravp.domain.Clase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ClaseService {
    private EntityManagerFactory emf;
    private EntityManager em;

    public ClaseService() {
        emf = Persistence.createEntityManagerFactory("UnidadPersistencia");
        em = emf.createEntityManager();
    }

    public Clase getClaseById(int id) {
        return em.find(Clase.class, id);
    }
    
    public Clase obtenerClasePorCodigo(){
        return null;
    }

    public void close() {
        em.close();
        emf.close();
    }
}
