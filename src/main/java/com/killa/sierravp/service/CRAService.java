package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.CRA;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.*;

public class CRAService {
    private EntityManagerFactory emf;

    // Constructor que inicializa la fábrica de EntityManager
    public CRAService() {
        emf = Persistence.createEntityManagerFactory("YourPersistenceUnitName"); 
    }

    // Método para calcular el CRA de cada alumno en una clase específica
    
    public void calcularCRAporClase(int claseId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            Clase clase = em.find(Clase.class, claseId);
            if (clase == null) {
                System.out.println("Clase no encontrada");
                return;
            }

            TypedQuery<Nota> query = em.createQuery("SELECT n FROM Nota n WHERE n.clase.id = :claseId", Nota.class);
            query.setParameter("claseId", claseId);
            List<Nota> notas = query.getResultList();

            // Utilizar un mapa para almacenar las notas de los alumnos
            
            Map<Alumno, List<Double>> notasMap = new HashMap<>();
            for (Nota nota : notas) {
                notasMap.computeIfAbsent(nota.getAlumno(), k -> new LinkedList<>()).add(nota.getValor());
            }

            int batchSize = 50; 
            int count = 0;

            for (Alumno alumno : clase.getAlumnos()) {
                double craValue = calcularCRAPorAlumno(alumno, notasMap.get(alumno));
                CRA cra = new CRA(alumno, clase, craValue);
                em.persist(cra);
                alumno.setCraPonderadoActual(craValue);
                em.merge(alumno);

                if (++count % batchSize == 0) {
                    em.flush();
                    em.clear();
                }
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Método privado para calcular el CRA de un alumno en una clase específica
    
    private double calcularCRAPorAlumno(Alumno alumno, List<Double> notas) {
        if (notas == null || notas.isEmpty()) {
            return 0.0;
        }

        double sumaNotas = 0.0;
        for (double nota : notas) {
            sumaNotas += nota;
        }

        return sumaNotas / notas.size();
    }
}
