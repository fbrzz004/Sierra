package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.repository.Universidad;

import java.util.List;
import java.util.stream.Collectors;

public class AlumnoService {
    private Universidad universidad;

    // Constructor que inicializa la clase AlumnoService con un objeto Universidad
    public AlumnoService(Universidad universidad) {
        this.universidad = universidad;
    }

    // Método para consultar el rendimiento de un alumno por su ID
    public Alumno consultarRendimiento(int id) {
        return getTodosLosAlumnos().stream()
                .filter(alumno -> alumno.getCodigo()== id)
                .findFirst()
                .orElse(null);
    }

    // Método para obtener todos los alumnos de la universidad
    public List<Alumno> getTodosLosAlumnos() {
        return universidad.getFacultades().values().stream()
                .flatMap(facultad -> facultad.getEscuelas().values().stream())
                .flatMap(escuela -> escuela.getAlumnos().stream())
                .collect(Collectors.toList());
    }

    // Método para obtener todos los alumnos en un formato de mapa (ID -> Alumno)
    public List<Alumno> obtenerTodosLosAlumnos() {
        return (List<Alumno>) getTodosLosAlumnos ().stream()
                .collect(Collectors.toMap(Alumno::getCodigo, alumno -> alumno));
    }
}
