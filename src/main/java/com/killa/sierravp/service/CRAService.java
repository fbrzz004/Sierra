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
import java.util.stream.Collectors;

public class CRAService {
    private EntityManagerFactory emf;

    // Constructor que inicializa la fábrica de sesiones de Hibernate
    
    public CRAService() {
        emf = Persistence.createEntityManagerFactory("UnidadPersistencia");
    }

    // Método para calcular el CRA de cada alumno en una clase específica
    
    public void calcularCRAporClase(int claseId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            
            // Obtener la clase por su ID
            
            Clase clase = em.find(Clase.class, claseId);
            if (clase == null) {
                System.out.println("Clase no encontrada");
                return;
            }

            // Obtener las notas de los alumnos en la clase
            
            TypedQuery<Nota> query = em.createQuery("SELECT n FROM Nota n WHERE n.clase.id = :claseId", Nota.class);
            query.setParameter("claseId", claseId);
            List<Nota> notas = query.getResultList();

            if (notas.isEmpty()) {
                System.out.println("No hay notas para esta clase");
                return;
            }

            // Calcular la media y la desviación estándar de las notas
            
            DoubleSummaryStatistics stats = notas.stream()
                    .mapToDouble(Nota::getValor)
                    .summaryStatistics();
            double media = stats.getAverage();
            double sumaCuadrados = notas.stream()
                    .mapToDouble(Nota::getValor)
                    .map(nota -> Math.pow(nota - media, 2))
                    .sum();
            double desviacionEstandar = Math.sqrt(sumaCuadrados / notas.size());

            // Usa hashmap para organizar las notas por alumno
            
            Map<Alumno, Double> craMap = new HashMap<>();

            // Calcular el CRA para cada alumno y almacenar en el mapa
            
            for (Nota nota : notas) {
                Alumno alumno = nota.getAlumno();
                double craValue = calcularCRAPorAlumno(nota.getValor(), media, desviacionEstandar, clase.getCurso().getCreditos());
                craMap.put(alumno, craValue);
            }

            // Guardar los valores del CRA en la base de datos
            
            for (Map.Entry<Alumno, Double> entry : craMap.entrySet()) {
                Alumno alumno = entry.getKey();
                double craValue = entry.getValue();

                // Crear y persistir el nuevo registro CRA
                
                CRA cra = new CRA(alumno, clase, craValue);
                em.persist(cra);

                // Actualizar el CRA ponderado actual del alumno
                
                alumno.setCraPonderadoActual(craValue);
                em.merge(alumno);
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

    // Método para calcular el CRA de un alumno usando la fórmula proporcionada
    
    private double calcularCRAPorAlumno(double nota, double media, double desviacionEstandar, int creditos) {
        if (desviacionEstandar == 0) {
            return 50; // Evita división por cero
        }
        return (((nota - media) / desviacionEstandar) * 10 * creditos) + 50;
    }
}
