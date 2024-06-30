/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AlumnoService {

    private SessionFactory sessionFactory;
    private static EntityManagerFactory emf;

    // Constructor que inicializa la fábrica de sesiones de Hibernate
    public AlumnoService() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        emf = Persistence.createEntityManagerFactory("UnidadPersistencia");
    }

    // Método para consultar el rendimiento de un alumno por su ID
    public Alumno findByID(int id) { //Anteriormente se llamo consultar rendimiento pero 
        //su funcion real es encontrar a un alumno asi que se renombro
        // Abrir una nueva sesión de Hibernate
        Session session = sessionFactory.openSession();
        // Obtener la entidad Alumno desde la base de datos
        Alumno alumno = session.get(Alumno.class, id);
        session.close();
        return alumno;
    }
    
    // Método para consultar el rendimiento de un alumno por su ID
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