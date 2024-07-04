package com.killa.sierravp.service;

import com.killa.sierravp.domain.*;
import com.killa.sierravp.repository.Universidad;
import com.killa.sierravp.repository.Universidad.EscuelaData;
import com.killa.sierravp.util.CodigoClaseGenerator;
import com.killa.sierravp.util.CodigoCursoGenerator;

import java.util.*;

public class CRAService {
    private Universidad universidad;
    private Map<Integer, Clase> claseCache;
    private Map<Integer, Ciclo> cicloCache;
    private Map<Integer, EscuelaProfesional> escuelaProfesionalCache;
    private Map<Integer, Universidad.FacultadData> facultadCache;

    // Constructor que inicializa la clase CRAService con un objeto Universidad
    public CRAService(Universidad universidad) {
        this.universidad = universidad;
        this.claseCache = new HashMap<>();
        this.cicloCache = new HashMap<>();
        this.escuelaProfesionalCache = new HashMap<>();
        this.facultadCache = new HashMap<>();
    }

    // Método para calcular el CRA de cada alumno en una clase específica
    public void calcularCRAporClase(int claseId) {
        // Obtener la clase por su ID usando el método auxiliar
        Clase clase = obtenerClasePorId(claseId);
        if (clase == null) {
            System.out.println("Clase no encontrada");
            return;
        }

        // Obtener las notas de los alumnos en la clase
        List<Nota> notas = clase.getNotas();
        if (notas.isEmpty()) {
            System.out.println("No hay notas para esta clase");
            return;
        }

        // Calcular la media y la desviación estándar de las notas
        double sum = 0.0, sumSq = 0.0;
        int n = notas.size();
        for (Nota nota : notas) {
            double valor = nota.getValor();
            sum += valor;
            sumSq += valor * valor;
        }
        double media = sum / n;
        double desviacionEstandar = Math.sqrt((sumSq / n) - (media * media));

        // Calcular el CRA para cada alumno y actualizar su CRA ponderado actual
        for (Nota nota : notas) {
            Alumno alumno = nota.getAlumno();
            double craValue = calcularCRAPorAlumno(nota.getValor(), media, desviacionEstandar, clase.getCurso().getCreditos());
            CRA cra = new CRA(alumno, clase, craValue);
            // Añadir el CRA al histórico del alumno
            alumno.getCraHistorico().add(cra);

            // Actualizar el CRA ponderado actual del alumno
            alumno.setCraPonderadoActual(craValue);
        }
    }

    // Método para calcular el CRA de un alumno usando la fórmula proporcionada
    private double calcularCRAPorAlumno(double nota, double media, double desviacionEstandar, int creditos) {
        if (desviacionEstandar == 0) {
            return 50; // Evita división por cero
        }
        return (((nota - media) / desviacionEstandar) * 10 * creditos) + 50;
    }

    // Método para calcular el CRA por ciclo
    public void calcularCRAPorCiclo(int cicloId) {
        // Obtener el ciclo por su ID usando el método auxiliar
        Ciclo ciclo = obtenerCicloPorId(cicloId);
        if (ciclo == null) {
            System.out.println("Ciclo no encontrado");
            return;
        }

        // Calcular el CRA para cada clase en el ciclo
        for (Clase clase : ciclo.getClases()) {
            calcularCRAporClase(clase.getId());
        }
    }

    // Método para calcular el CRA por Escuela Profesional
    public void calcularCRAPorEscuelaProfesional(int escuelaProfesionalId) {
        // Obtener la Escuela Profesional por su ID usando el método auxiliar
        EscuelaProfesional escuelaProfesional = obtenerEscuelaProfesionalPorId(escuelaProfesionalId);
        if (escuelaProfesional == null) {
            System.out.println("Escuela Profesional no encontrada");
            return;
        }

        // Calcular el CRA para cada ciclo en la Escuela Profesional
        for (Ciclo ciclo : escuelaProfesional.getCiclos()) {
            calcularCRAPorCiclo(ciclo.getId());
        }
    }

    // Método para calcular el CRA por facultad
    public void calcularCRAPorFacultad(int facultadId) {
        // Obtener la facultad por su ID usando el método auxiliar
        Universidad.FacultadData facultad = obtenerFacultadPorId(facultadId);
        if (facultad == null) {
            System.out.println("Facultad no encontrada");
            return;
        }

        // Calcular el CRA para cada Escuela Profesional en la facultad
        for (EscuelaData escuelaData : facultad.getEscuelas().values()) {
            for (EscuelaProfesional escuelaProfesional : escuelaData.getEscuelasProfesionales()) {
                calcularCRAPorEscuelaProfesional(escuelaProfesional.getId());
            }
        }
    }

    // Método para mostrar las notas de un alumno por su código
    public void mostrarNotasAlumno(int codigoAlumno) {
        Alumno alumno = universidad.obtenerAlumnoPorId(codigoAlumno);
        if (alumno == null) {
            System.out.println("Alumno no encontrado.");
            return;
        }

        System.out.println("Notas del Alumno: " + alumno.getPrimerNombre() + " " + alumno.getPrimerApellido());
        for (Nota nota : alumno.getNotas()) {
            System.out.println("Curso: " + nota.getCurso().getNombre() + " - Nota: " + nota.getCalificacion() + " (" + nota.getTipo() + ")");
        }
    }

    // Métodos auxiliares para obtener entidades con caché
    private Clase obtenerClasePorId(int claseId) {
        return claseCache.computeIfAbsent(claseId, universidad::obtenerClasePorId);
    }

    private Ciclo obtenerCicloPorId(int cicloId) {
        return cicloCache.computeIfAbsent(cicloId, universidad::obtenerCicloPorId);
    }

    private EscuelaProfesional obtenerEscuelaProfesionalPorId(int escuelaProfesionalId) {
        return escuelaProfesionalCache.computeIfAbsent(escuelaProfesionalId, universidad::obtenerEscuelaProfesionalPorId);
    }

    private Universidad.FacultadData obtenerFacultadPorId(int facultadId) {
        return facultadCache.computeIfAbsent(facultadId, universidad::obtenerFacultadPorId);
    }
}
