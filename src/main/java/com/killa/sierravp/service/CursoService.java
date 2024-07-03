package com.killa.sierravp.service;

import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Curso;
import com.killa.sierravp.domain.Nota;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class CursoService {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadPersistencia");

    public Clase obtenerClase(int idClase) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Clase.class, idClase);
        } finally {
            em.close();
        }
    }

    public List<Nota> obtenerNotasPorClase(int idClase) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Nota> query = em.createQuery("SELECT n FROM Nota n WHERE n.clase.id = :idClase", Nota.class);
            query.setParameter("idClase", idClase);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public double calcularMedia(List<Nota> notas) {
        return notas.stream().mapToInt(Nota::getCalificacion).average().orElse(0);
    }

    public int obtenerNotaMinima(List<Nota> notas) {
        return notas.stream().mapToInt(Nota::getCalificacion).min().orElse(0);
    }

    public int obtenerNotaMaxima(List<Nota> notas) {
        return notas.stream().mapToInt(Nota::getCalificacion).max().orElse(0);
    }
    
    public List<Curso> getCursosByClaseId(int codigoClase) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Curso> query = em.createQuery("SELECT n FROM Curso n WHERE n.clases.id = :codigoClase", Curso.class);
            query.setParameter("codigoClase", codigoClase);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
