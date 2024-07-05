package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.repository.Universidad;
import com.killa.sierravp.util.TipoNota;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
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
    
    // Método para generar notas para los alumnos en una clase específica
    public void generarNotasParaClase(Clase clase) {
        if (clase == null) {
            System.out.println("Clase no encontrada");
            return;
        }

        Random random = new Random();
        for (Alumno alumno : clase.getAlumnos()) {
            Set<Nota> notas = new HashSet<>();
            notas.add(generarNota(alumno, clase, TipoNota.EC, random));
            notas.add(generarNota(alumno, clase, TipoNota.EP, random));
            notas.add(generarNota(alumno, clase, TipoNota.EF, random));
            clase.getNotas().addAll(notas);
            alumno.getNotas().addAll(notas);
        }
    }

    private Nota generarNota(Alumno alumno, Clase clase, TipoNota tipo, Random random) {
        Nota nota = new Nota(alumno, clase.getCurso(), clase);
        nota.setTipo(tipo);
        nota.setCalificacion(random.nextInt(21)); // Genera una nota entre 0 y 20
        return nota;
    }
}

