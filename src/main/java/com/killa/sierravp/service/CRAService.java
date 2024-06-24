package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.CRA;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class CRAService {
    private EntityManagerFactory emf;

    // Constructor que inicializa la fábrica de EntityManager
    public CRAService() {
        emf = Persistence.createEntityManagerFactory("YourPersistenceUnitName"); // Asegúrate de que el nombre de la unidad de persistencia sea el correcto.
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

            for (Alumno alumno : clase.getAlumnos()) {
                double craValue = calcularCRAPorAlumno(alumno, notas);
                CRA cra = new CRA(alumno, clase, craValue);
                em.persist(cra);
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

    // Método privado para calcular el CRA de un alumno en una clase específica
    private double calcularCRAPorAlumno(Alumno alumno, List<Nota> notas) {
        double sumaNotas = 0.0;
        int countNotas = 0;

        for (Nota nota : notas) {
            if (nota.getAlumno().equals(alumno)) {
                sumaNotas += nota.getValor();
                countNotas++;
            }
        }

        return countNotas > 0 ? sumaNotas / countNotas : 0.0;
    }
}