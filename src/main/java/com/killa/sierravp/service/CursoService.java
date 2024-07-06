package com.killa.sierravp.service;

import com.killa.sierravp.domain.Curso;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.repository.Universidad;

import java.util.List;
import java.util.stream.Collectors;

public class CursoService {
    private Universidad universidad;

    // Constructor que inicializa la clase CursoService con un objeto Universidad
    public CursoService(Universidad universidad) {
        this.universidad = universidad;
    }

    // Método para calcular la media de las calificaciones en una lista de notas
    public double calcularMedia(List<Nota> notas) {
        return notas.stream().mapToInt(Nota::getCalificacion).average().orElse(0);
    }

    // Método para obtener la calificación mínima en una lista de notas
    public int obtenerNotaMinima(List<Nota> notas) {
        return notas.stream().mapToInt(Nota::getCalificacion).min().orElse(0);
    }

    // Método para obtener la calificación máxima en una lista de notas
    public int obtenerNotaMaxima(List<Nota> notas) {
        return notas.stream().mapToInt(Nota::getCalificacion).max().orElse(0);
    }

    // Método para obtener cursos por el ID de una clase
    public List<Curso> getCursosByClaseId(int codigoClase) {
        return universidad.getFacultades().values().stream()
                .flatMap(facultad -> facultad.getEscuelas().values().stream())
                .flatMap(escuela -> escuela.getClases().stream())
                .filter(clase -> clase.getId() == codigoClase)
                .map(clase -> clase.getCurso())
                .collect(Collectors.toList());
    }
}