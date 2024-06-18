/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 *
 * @author USER
 */

public class AlumnoService {
    private SessionFactory sessionFactory;

    // Constructor que inicializa la fábrica de sesiones de Hibernate
    
    public AlumnoService() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }
    
    // Método para consultar el rendimiento de un alumno por su ID
    
    public Alumno consultarRendimiento(int id) {
        
        try (Session session = sessionFactory.openSession()) {
            
            return session.get(Alumno.class, id);
            
        } catch (Exception e) {
            
            System.err.println("Error al consultar el rendimiento del alumno: " + e.getMessage());
            
            return null;
        }
    }

    // Método para obtener una lista de todos los alumnos
    
    public List<Alumno> obtenerTodosLosAlumnos() {
        
        try (Session session = sessionFactory.openSession()) {
            
            Query<Alumno> query = session.createQuery("from Alumno", Alumno.class);
            
            return query.list();
            
        } catch (Exception e) {
            
            System.err.println("Error al obtener la lista de alumnos: " + e.getMessage());
            
            return null;
        }
    }

    // Método para actualizar la información de un alumno
    
    
    public boolean actualizarAlumno(Alumno alumno) {
        
        Transaction transaction = null;
        
        try (Session session = sessionFactory.openSession()) {
            
            transaction = session.beginTransaction();
            session.update(alumno);
            transaction.commit();
            
            return true;
            
        } catch (Exception e) {
            
            if (transaction != null) transaction.rollback();
            
            System.err.println("Error al actualizar el alumno: " + e.getMessage());
            
            return false;
        }
    }
    
    // Método nuevo para calcular el CRA de todos los alumnos en un curso
    
    public void calcularCRA(int courseId) {
        
    try (Session session = sessionFactory.openSession()) {
        
        List<Alumno> alumnos = session.createQuery("FROM Alumno WHERE courseId = :courseId", Alumno.class)
                
                                      .setParameter("courseId", courseId)
                                      .list();
        
        double media = alumnos.stream().mapToDouble(Alumno::getCraPonderadoActual).average().orElse(0);
        double desviacionEstandar = Math.sqrt(alumnos.stream()
                
                                                     .mapToDouble(a -> Math.pow(a.getCraPonderadoActual() - media, 2))
                                                     .average().orElse(0));
        
        Transaction tx = session.beginTransaction();
        
        for (Alumno alumno : alumnos) {
            
            double cra = (alumno.getCraPonderadoActual() - media) / desviacionEstandar;
            cra = cra * 10 * alumno.getClases().iterator().next().getCreditos() + 50;
            alumno.setCraPonderadoActual(cra);
            session.update(alumno);
        }
        tx.commit();
        
    } catch (Exception e) {
        System.err.println("Error al calcular CRA: " + e.getMessage());
    }
    
    
    }
}
