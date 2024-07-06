package com.killa.sierravp.service;

import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.repository.ClaseRepository;
import com.killa.sierravp.repository.Universidad;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class ClaseService {
    private ClaseRepository claseRepository;
    private Universidad universidad;

    public ClaseService(Universidad universidad) {
        this.universidad = universidad;
        this.claseRepository = new ClaseRepository();
    }

    public Clase getClaseById(int id) {
        return claseRepository.findById(id);
    }

    public List<Clase> getAllClases() {
        return claseRepository.findAll();
    }

    public List<Clase> getClasesByAlumnoId(int codigoAlumno) {
        return universidad.getFacultades().values().stream()
                .flatMap(facultad -> facultad.getEscuelas().values().stream())
                .flatMap(escuela -> escuela.getClases().stream())
                .filter(clase -> clase.getAlumnos().stream().anyMatch(alumno -> alumno.getCodigo() == codigoAlumno))
                .collect(Collectors.toList());
    }

    public List<Clase> getClasesByProfesorId(int codigoProfesor) {
        return universidad.getFacultades().values().stream()
                .flatMap(facultad -> facultad.getEscuelas().values().stream())
                .flatMap(escuela -> escuela.getClases().stream())
                .filter(clase -> clase.getProfesor().getDNI() == codigoProfesor)
                .collect(Collectors.toList());
    }

    public String obtenerEstadisticasDeClase(int idClase) {
        Clase clase = getClaseById(idClase);
        if (clase == null) {
            return "Clase no encontrada";
        }

        List<Nota> notas = clase.getNotas();
        OptionalDouble promedio = notas.stream().mapToInt(Nota::getCalificacion).average();
        long aprobados = notas.stream().filter(nota -> nota.getCalificacion() >= 11).count();
        long desaprobados = notas.size() - aprobados;

        return "Estadísticas de la clase ID: " + idClase + "\n" +
                "Promedio de notas: " + (promedio.isPresent() ? promedio.getAsDouble() : "N/A") + "\n" +
                "Número de aprobados: " + aprobados + "\n" +
                "Número de desaprobados: " + desaprobados;
    }
}
