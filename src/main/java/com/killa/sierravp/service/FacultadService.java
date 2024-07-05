/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.service;

import com.killa.sierravp.domain.Facultad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author karlo
 */
public class FacultadService {

    private static EntityManagerFactory emf;

    // Constructor que inicializa la fábrica de sesiones de Hibernate
    public FacultadService() {
        emf = Persistence.createEntityManagerFactory("UnidadPersistencia");
    }
    
    public void create(Facultad facultad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(facultad);
        em.getTransaction().commit();
        em.close();
    }
    
    public int obtenerIdFacultadPorNombre(String nombreFacultad) {
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Integer> query = em.createQuery(
                    "SELECT f.id FROM Facultad f WHERE f.nombre = :nombre", Integer.class);
            query.setParameter("nombre", nombreFacultad);
            return query.getSingleResult();
        } catch (Exception e) {
            // Manejo de excepciones (puedes personalizar según tu necesidad)
            throw new RuntimeException("Error al obtener el id de la facultad por nombre: " + nombreFacultad, e);
        }
    }
    
    public Facultad obtenerFacultadPorNombreConEscuelas(String nombreFacultad) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Facultad> query = em.createQuery(
                    "SELECT f FROM Facultad f " +
                    "LEFT JOIN FETCH f.ep ep " +
                    "WHERE f.nombre = :nombre", Facultad.class);
            query.setParameter("nombre", nombreFacultad);
            return query.getSingleResult();
        }
    }
}
