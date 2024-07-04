package com.killa.sierravp.service;

import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.repository.Universidad;
import java.util.List;
import java.util.stream.Collectors;

public class NotaService {
    private Universidad universidad;

    public NotaService(Universidad universidad) {
        this.universidad = universidad;
    }

    public List<Nota> obtenerNotasPorCodigoAlumno(int codigoAlumno, int codigoCurso) {
        return universidad.getFacultades().values().stream()
                .flatMap(facultad -> facultad.getEscuelas().values().stream())
                .flatMap(escuela -> escuela.getClases().stream())
                .filter(clase -> clase.getCurso().getId() == codigoCurso)
                .flatMap(clase -> clase.getNotas().stream())
                .filter(nota -> nota.getAlumno().getCodigo() == codigoAlumno)
                .collect(Collectors.toList());
    }
}
