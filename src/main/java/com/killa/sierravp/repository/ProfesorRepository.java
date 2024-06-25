package com.killa.sierravp.repository;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class ProfesorRepository {

    EntityManager entityManager;

    public ProfesorRepository() {
        entityManager = Persistence.createEntityManagerFactory("UnidadPersistencia").createEntityManager();
    }

    public Clase findClaseByID(int id) {
        try {
            return entityManager.find(Clase.class, id);
        } finally {
            entityManager.close();
        }
    }

    public List<Alumno> obtenerAlumnosPorClase(int claseId) {
        try {
            return entityManager.createQuery("SELECT a FROM Alumno a JOIN a.notas n WHERE n.clase.id = :claseId", Alumno.class)
                    .setParameter("claseId", claseId)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public boolean guardarNota(Nota nota) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(nota);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }
}
