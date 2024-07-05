package com.killa.sierravp.service;

import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Profesor;
import com.killa.sierravp.repository.Universidad;

import java.util.List;
import java.util.stream.Collectors;

public class ClaseService {
    private Universidad universidad;

    // Constructor que inicializa la clase ClaseService con un objeto Universidad
    public ClaseService(Universidad universidad) {
        this.universidad = universidad;
    }

    // Método para obtener una clase por su ID
    public Clase getClaseById(int id) {
        return universidad.getFacultades().values().stream()
                .flatMap(facultad -> facultad.getEscuelas().values().stream())
                .flatMap(escuela -> escuela.getClases().stream())
                .filter(clase -> clase.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Método para obtener clases por el ID de un alumno
    public List<Clase> getClasesByAlumnoId(int codigoAlumno) {
        return universidad.getFacultades().values().stream()
                .flatMap(facultad -> facultad.getEscuelas().values().stream())
                .flatMap(escuela -> escuela.getClases().stream())
                .filter(clase -> clase.getAlumnos().stream().anyMatch(alumno -> alumno.getCodigo() == codigoAlumno))
                .collect(Collectors.toList());
    }

    // Método para obtener clases por el ID de un profesor
    public List<Clase> getClasesByProfesorId(int codigoProfesor) {
        return universidad.getFacultades().values().stream()
                .flatMap(facultad -> facultad.getEscuelas().values().stream())
                .flatMap(escuela -> escuela.getClases().stream())
                .filter(clase -> clase.getProfesor().getDNI() == codigoProfesor)
                .collect(Collectors.toList());
    }
}
