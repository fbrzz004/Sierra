package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Servicio para gestionar alumnos utilizando JPA.
 */
public class AlumnoService {
    private EntityManagerFactory emf;

    // Constructor que inicializa la fábrica de EntityManager
    public AlumnoService() {
        emf = Persistence.createEntityManagerFactory("YourPersistenceUnitName"); // Reemplaza "YourPersistenceUnitName" con el nombre de tu unidad de persistencia configurada en persistence.xml
    }

    // Método para consultar el rendimiento de un alumno por su ID
    public Alumno consultarRendimiento(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Alumno.class, id);
        } catch (Exception e) {
            System.err.println("Error al consultar el rendimiento del alumno: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    // Método para obtener una lista de todos los alumnos
    public List<Alumno> obtenerTodosLosAlumnos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Alumno> query = em.createQuery("SELECT a FROM Alumno a", Alumno.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al obtener la lista de alumnos: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    // Método para actualizar la información de un alumno
    public boolean actualizarAlumno(Alumno alumno) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(alumno);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al actualizar el alumno: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    // Método para calcular el CRA de todos los alumnos en un curso
    public void calcularCRA(int courseId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Alumno> query = em.createQuery("SELECT a FROM Alumno a WHERE a.curso.id = :courseId", Alumno.class);
            query.setParameter("courseId", courseId);
            List<Alumno> alumnos = query.getResultList();
            
            double media = alumnos.stream().mapToDouble(Alumno::getCraPonderadoActual).average().orElse(0);
            double desviacionEstandar = Math.sqrt(alumnos.stream().mapToDouble(a -> Math.pow(a.getCraPonderadoActual() - media, 2)).average().orElse(0));

            em.getTransaction().begin();
            for (Alumno alumno : alumnos) {
                double cra = (alumno.getCraPonderadoActual() - media) / desviacionEstandar;
                cra = cra * 10 * alumno.getClases().iterator().next().getCurso().getCreditos() + 50; // Asegúrate de que el método getCreditos() esté definido en la clase Curso
                alumno.setCraPonderadoActual(cra);
                em.merge(alumno);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al calcular CRA: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}