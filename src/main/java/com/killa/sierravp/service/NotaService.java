package com.killa.sierravp.service;

import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.repository.NotaRepository;

import java.util.List;

public class NotaService {

    NotaRepository nr = new NotaRepository();

    public List<Nota> obtenerNotasPorCodigoAlumno(int codigoAlumno, int idCurso) {
       return nr.obtenerNotasPorAlumnoYCurso(codigoAlumno, idCurso);
    }
}
