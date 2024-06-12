/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.CRA;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

/**
 *
 * @author USER
 */

public class CRAService {
    private SessionFactory sessionFactory;

    // Constructor que inicializa la fábrica de sesiones de Hibernate
    
    public CRAService() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // Método para calcular el CRA de cada alumno en una clase específica
    
    public void calcularCRAporClase(int claseId) {
       
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            
            // Obtener la clase por su ID
            
            Clase clase = session.get(Clase.class, claseId);
            if (clase == null) {
                System.out.println("Clase no encontrada");
                return;
            }

            // Obtener las notas de los alumnos en la clase
            
            List<Nota> notas = session.createQuery("FROM Nota WHERE clase.id = :claseId", Nota.class)
                    .setParameter("claseId", claseId)
                    .getResultList();

            // Calcular el CRA para cada alumno
            
             // Calcular el CRA para cada alumno
            for (Alumno alumno : clase.getAlumnos()) {
                double craValue = calcularCRAPorAlumno(alumno, notas);
                CRA cra = new CRA(alumno, clase, craValue);
                session.save(cra);

                // Actualizar el CRA ponderado actual del alumno
                
                alumno.setCraPonderadoActual(craValue);
                session.update(alumno);
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para calcular el CRA de un alumno en una clase específica
    private double calcularCRAPorAlumno(Alumno alumno, List<Nota> notas) {
        double sumaNotas = 0.0;
        int countNotas = 0;

        for (Nota nota : notas) {
            if (nota.getAlumno().equals(alumno)) {
                sumaNotas += nota.getValor();
                countNotas++;
            }
        }

        // Si no hay notas para el alumno, evitar la división por cero
        if (countNotas == 0) {
            return 0.0;
        }

        return sumaNotas / countNotas;
    }
}