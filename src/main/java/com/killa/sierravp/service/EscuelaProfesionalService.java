/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.service;

import com.killa.sierravp.domain.EscuelaProfesional;
import com.killa.sierravp.domain.Facultad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author karlo
 */
public class EscuelaProfesionalService {
    private static EntityManagerFactory emf;

    // Constructor que inicializa la fábrica de sesiones de Hibernate
    public EscuelaProfesionalService() {
        emf = Persistence.createEntityManagerFactory("UnidadPersistencia");
    }
    
    public void create(EscuelaProfesional escuelaProfesional) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(escuelaProfesional);
        em.getTransaction().commit();
        em.close();
    }
}
