package com.killa.sierravp.service;

import com.killa.sierravp.domain.Clase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class ClaseService {
    private EntityManagerFactory emf;
    private EntityManager em;

    public ClaseService() {
        emf = Persistence.createEntityManagerFactory("UnidadPersistencia");
        em = emf.createEntityManager();
    }

    public Clase getClaseById(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Clase.class, id);
    }

    public void close() {
        em.close();
        emf.close();
    }
    
    public List<Clase> getClasesByAlumnoId(int codigoAlumno) {
        try {
            return em.createQuery(
                    "SELECT a FROM Clase a WHERE a.alumnos.codigo = :codigoAlumno", Clase.class)
                    .setParameter("codigoAlumno", codigoAlumno)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Clase> getClasesByProfesorId(int codigoProfesor) {
       try {
            return em.createQuery(
                    "SELECT a FROM Clase a WHERE a.profesor.DNI = :codigoProfesor", Clase.class)
                    .setParameter("codigoProfesor", codigoProfesor)
                    .getResultList();
        } finally {
            em.close();
        } 
    }
}
