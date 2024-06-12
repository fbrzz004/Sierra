/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
        
        // Abrir una nueva sesión de Hibernate
        
        Session session = sessionFactory.openSession();
        
        // Obtener la entidad Alumno desde la base de datos
        
        Alumno alumno = session.get(Alumno.class, id);
  
        session.close();
        return alumno;
    }
}