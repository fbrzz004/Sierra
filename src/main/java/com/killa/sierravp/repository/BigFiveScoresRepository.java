/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.repository;

import com.killa.sierravp.domain.BigFiveScores;
import com.killa.sierravp.domain.InteresesAcademicos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author karlo
 */
public class BigFiveScoresRepository {
    EntityManager em;

    public BigFiveScoresRepository() {
        em=Persistence.createEntityManagerFactory("UnidadPersistencia").createEntityManager();
    }
    
    public BigFiveScores obtenerByCodigo (int codigo){
        try {
            TypedQuery<BigFiveScores> query = em.createQuery(
                "SELECT bfs FROM InteresesAcademicos bfs WHERE bfs.alumno.codigo = :codigo_alumno", BigFiveScores.class);
            query.setParameter("codigo_alumno", codigo);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
