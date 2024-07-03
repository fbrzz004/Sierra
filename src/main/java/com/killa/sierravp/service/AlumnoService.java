/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Set;

public class AlumnoService {

    private static EntityManagerFactory emf;

    // Constructor que inicializa la fábrica de sesiones de Hibernate
    public AlumnoService() {
        emf = Persistence.createEntityManagerFactory("UnidadPersistencia");
    }
    
    // Método para consultar el rendimiento de un alumno por su ID
    public Alumno findByCodigo(int codigo) {
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.createQuery(
                "SELECT a FROM Alumno a " +
                "LEFT JOIN FETCH a.clases c " +
                "LEFT JOIN FETCH c.curso cu " +
                "LEFT JOIN FETCH cu.clases " +
                "WHERE a.codigo = :codigo", Alumno.class)
                .setParameter("codigo", codigo)
                .getSingleResult();
        } finally {
            em.close();
        }
    }
    /*Metodo anterior que solo recuperaba alumno pero no sus clases relacionadas
    public Alumno findByCodigo(int codigo) { //Anteriormente se llamo consultar rendimiento pero 
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Alumno> query = em.createQuery(
                    "SELECT a FROM Alumno a WHERE a.codigo = :codigo", Alumno.class);
            query.setParameter("codigo", codigo);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    */
    

    public void create(Alumno alumno) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(alumno);
        em.getTransaction().commit();
        em.close();
    }
    
    // Método para obtener todos los alumnos de una facultad
    public List<Alumno> allAlumnosFromFacultad(int facultadID) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Alumno> query = em.createQuery(
                    "SELECT a FROM Alumno a WHERE a.facultad.id = :facultadID", Alumno.class);
            query.setParameter("facultadID", facultadID);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Alumno> getAlumnosByClaseId(int codigoClase) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "SELECT a FROM Alumno a WHERE a.clases.id :codigoClase", Alumno.class)
                    .setParameter("codigoClase", codigoClase)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
/*

public List<Alumno> allAlumnosFromFacultad(int facultadID) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Alumno> query = em.createQuery(
                    "SELECT a FROM Alumno a WHERE a.facultad.id = :facultadID", Alumno.class);
            query.setParameter("facultadID", facultadID);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
*/