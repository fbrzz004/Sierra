package com.killa.sierravp.service;

import com.killa.sierravp.domain.*;
import com.killa.sierravp.repository.Universidad;

import java.util.*;
import java.util.stream.Collectors;
import java.util.DoubleSummaryStatistics;

public class CRAService {
    private Universidad universidad;

    // Constructor que inicializa la clase CRAService con un objeto Universidad
    
    public CRAService(Universidad universidad) {
        this.universidad = universidad;
    }

    // Método para calcular el CRA de un alumno por su ID
    
    public void calcularCRAPorAlumno(int codigoAlumno) {
        Alumno alumno = universidad.obtenerAlumnoPorId(codigoAlumno);
        if (alumno == null) {
            System.out.println("Alumno no encontrado");
            return;
        }

        List<Nota> notas = new ArrayList<>(alumno.getNotas());
        if (notas.isEmpty()) {
            System.out.println("No hay notas para este alumno");
            return;
        }

        // Calcular la media y la desviación estándar de las notas
        
        DoubleSummaryStatistics stats = notas.stream()
                .mapToDouble(Nota::getCalificacion)
                .summaryStatistics();
        double media = stats.getAverage();
        double sumaCuadrados = notas.stream()
                .mapToDouble(Nota::getCalificacion)
                .map(nota -> Math.pow(nota - media, 2))
                .sum();
        double desviacionEstandar = Math.sqrt(sumaCuadrados / notas.size());

        // Calcular el CRA ponderado actual del alumno
        
        double sumaCRAPonderados = 0;
        double sumaCreditos = 0;
        for (Nota nota : notas) {
            double craValue = calcularCRAPorNota(nota.getCalificacion(), media, desviacionEstandar, nota.getClase().getCurso().getCreditos());
            sumaCRAPonderados += craValue * nota.getClase().getCurso().getCreditos();
            sumaCreditos += nota.getClase().getCurso().getCreditos();
        }

        double craPonderadoActual = sumaCRAPonderados / sumaCreditos;

        // Actualizar el CRA ponderado actual del alumno
        
        alumno.setCraPonderadoActual(craPonderadoActual);
    }

    // Método para calcular el CRA de una nota específica
    
    private double calcularCRAPorNota(double nota, double media, double desviacionEstandar, int creditos) {
        if (desviacionEstandar == 0) {
            return 50; // Evita división por cero
        }
        return (((nota - media) / desviacionEstandar) * 10 * creditos) + 50;
    }

    // Método para mostrar las notas de un alumno por su código
    
    public void mostrarNotasAlumno(int codigoAlumno) {
        Alumno alumno = universidad.obtenerAlumnoPorId(codigoAlumno);
        if (alumno == null) {
            System.out.println("Alumno no encontrado.");
            return;
        }

        System.out.println("Notas del Alumno: " + alumno.getPrimerNombre() + " " + alumno.getPrimerApellido());
        System.out.println();

        // Agrupar notas por curso y mostrar tres notas por curso
        
        Map<String, List<Nota>> notasPorCurso = alumno.getNotas().stream()
                .collect(Collectors.groupingBy(nota -> nota.getCurso().getNombre()));

        for (Map.Entry<String, List<Nota>> entry : notasPorCurso.entrySet()) {
            String nombreCurso = entry.getKey();
            List<Nota> notas = entry.getValue();
            System.out.println("Curso: " + nombreCurso);
            for (Nota nota : notas) {
                System.out.println("  Nota: " + nota.getCalificacion() + " (" + nota.getTipo() + ")");
            }
        }
    }

    
}
