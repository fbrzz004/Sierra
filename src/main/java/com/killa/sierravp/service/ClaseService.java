package com.killa.sierravp.service;

import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.repository.ClaseRepository;
import com.killa.sierravp.repository.NotaRepository;
import com.killa.sierravp.util.TipoNota;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClaseService {
    private ClaseRepository claseRepository;
    private NotaRepository notaRepository;

    public ClaseService(ClaseRepository claseRepository, NotaRepository notaRepository) {
        this.claseRepository = claseRepository;
        this.notaRepository = notaRepository;
    }

    public Clase getClaseById(int id) {
        return claseRepository.findById(id);
    }

    public List<Clase> getClasesByAlumnoId(int codigoAlumno) {
        return claseRepository.findByAlumnoId(codigoAlumno);
    }

    public List<Clase> getClasesByProfesorId(int codigoProfesor) {
        return claseRepository.findByProfesorId(codigoProfesor);
    }

    public String obtenerEstadisticasClase(int claseId) {
        List<Nota> notas = notaRepository.findByClaseId(claseId);
        if (notas.isEmpty()) {
            return "No se encontraron notas para la clase.";
        }

        Map<TipoNota, DoubleSummaryStatistics> estadisticas = notas.stream()
                .collect(Collectors.groupingBy(
                        Nota::getTipo,
                        Collectors.summarizingDouble(Nota::getCalificacion)
                ));

        StringBuilder sb = new StringBuilder();
        estadisticas.forEach((tipo, stats) -> {
            sb.append("Tipo de nota: ").append(tipo).append("\n");
            sb.append("Promedio: ").append(stats.getAverage()).append("\n");
            sb.append("Máximo: ").append(stats.getMax()).append("\n");
            sb.append("Mínimo: ").append(stats.getMin()).append("\n");
            sb.append("Cantidad: ").append(stats.getCount()).append("\n\n");
        });

        return sb.toString();
    }
}
