/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.repository;

import com.killa.sierravp.domain.InteresesAcademicos;
import com.killa.sierravp.domain.Usuario;
import com.sun.source.tree.TryTree;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author karlo
 */
public class InteresesAcademyRepository {
    EntityManager entityManager;

    public InteresesAcademyRepository() {
        entityManager = Persistence.createEntityManagerFactory("UnidadPersistencia").createEntityManager();
    }
    
    public InteresesAcademicos obtenerIntAcdAlumno(int codigo){
        try {
            TypedQuery<InteresesAcademicos> query = entityManager.createQuery(
                "SELECT i FROM InteresesAcademicos i WHERE i.alumno.codigo = :codigo_alumno", InteresesAcademicos.class);
            query.setParameter("codigo_alumno", codigo);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
