package com.killa.sierravp.service;

import com.killa.sierravp.domain.Nota;

import java.util.List;

public class CursoService {

    

    public double calcularMedia(List<Nota> notas) {
        return notas.stream().mapToInt(Nota::getCalificacion).average().orElse(0);
    }

    public int obtenerNotaMinima(List<Nota> notas) {
        return notas.stream().mapToInt(Nota::getCalificacion).min().orElse(0);
    }

    public int obtenerNotaMaxima(List<Nota> notas) {
        return notas.stream().mapToInt(Nota::getCalificacion).max().orElse(0);
    }
}
