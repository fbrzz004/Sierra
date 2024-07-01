package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlumnoService {
    private EntityManagerFactory emf;

    // Constructor que inicializa la fábrica de sesiones de Hibernate
    
    public AlumnoService() {
        
        emf = Persistence.createEntityManagerFactory("UnidadPersistencia"); 
    }

    // Método para consultar el rendimiento de un alumno por su ID
    
    public Alumno consultarRendimiento(int id) {
        EntityManager em = emf.createEntityManager();
        Alumno alumno = null;
        try {
            
            // Obtiene la entidad Alumno desde la base de datos usando su ID
            
            alumno = em.find(Alumno.class, id);
        } catch (Exception e) {
            
            e.printStackTrace();
            
        } finally {
            
            em.close();
        }
        return alumno;
    }
    
    // Método para obtener todos los alumnos 
    
    public Map<Integer, Alumno> obtenerTodosLosAlumnos() {
        EntityManager em = emf.createEntityManager();
        Map<Integer, Alumno> alumnosMap = new HashMap<>();
        try {
            
            // Consulta todos los alumnos y los almacena en un mapa para un acceso rápido por ID
            
            List<Alumno> alumnos = em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();
            for (Alumno alumno : alumnos) {
                alumnosMap.put(alumno.getCodigo(), alumno);
            }
        } catch (Exception e) {
  
            e.printStackTrace();
            
        } finally {
            
            em.close();
        }
        return alumnosMap;
    }
}