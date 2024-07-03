package com.killa.sierravp.service;

import com.killa.sierravp.domain.Nota;

import java.util.List;

public class CursoService {

    

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
