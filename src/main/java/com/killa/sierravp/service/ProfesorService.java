package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.repository.ProfesorRepository;

import java.util.List;

public class ProfesorService {

    ProfesorRepository pr = new ProfesorRepository();

    public Clase findClaseByID(int id) {
        return pr.findClaseByID(id);
    }

    public List<Alumno> obtenerAlumnosPorClase(int claseId) {
        return pr.obtenerAlumnosPorClase(claseId);
    }

    public boolean guardarNota(Nota nota) {
        return pr.guardarNota(nota);
    }
}
