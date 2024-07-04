package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.repository.NotaRepository;
import com.killa.sierravp.repository.Universidad;
import com.killa.sierravp.util.TipoNota;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.killa.sierravp.util.AlumnoWrapper;
import java.util.ArrayList;
import java.util.Set;

public class NotaService {
    private Universidad universidad;
    private NotaRepository notaRepository = new NotaRepository();

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
    
    public List<Nota> obtenerNotasPorCodigoAlumnoCodigoCurso(Alumno alumno, int codigoCurso) {
        List<Nota> notasCurso = new ArrayList<>();
        Set<Nota> notas = alumno.getNotas();
        for(Nota nota: notas) {
            //System.out.println("alumno tiene nota " + nota.getCalificacion() + "del tipo " + nota.getTipo() + "para curso " + nota.getCurso().getId());
            if (codigoCurso == nota.getCurso().getId()) {
                notasCurso.add(nota);
            }
            
        }
        return notasCurso;
    }

    public Alumno obtenerKesimoAlumnoPorNotaFinal(List<Alumno> alumnos, int idCurso, int posicionK) {
        List<AlumnoWrapper> alumnosWrapper = new ArrayList<>();
        //System.out.println("numero de alumnos total:: " + alumnos.size());
        
        int iterationCount = 0;
        int maxIterations = 100;
        for (int i = 0; i < maxIterations; i++) {
            iterationCount++;
            Alumno alumno = alumnos.get(i);
            //System.out.println("obteniendo notas para alumno " + alumno.getCorreo() + " y curso id " + idCurso);
            List<Nota> notasAlumno = obtenerNotasPorCodigoAlumnoCodigoCurso(alumno, idCurso); //notaRepository.obtenerNotasPorAlumnoYCurso(alumno.getCodigo(), idCurso);
            if (notasAlumno.isEmpty()) {
                //System.out.println("no se encontraron notas para alumno " + alumno.getCorreo() + " y curso id " + idCurso);
                continue;
            }

            Optional<Nota> notaFinal = notasAlumno.stream()
                .filter(nota -> TipoNota.EF == nota.getTipo())
                .findFirst();
            
            if(notaFinal.isPresent()) {
                AlumnoWrapper aw = new AlumnoWrapper(alumno, notaFinal.get().getCalificacion());
                //System.out.println("" + aw.getAlumno().getNombre() + "tiene nota final "+ notaFinal.get().getCalificacion());
                alumnosWrapper.add(aw);
                
            } else {
                //System.out.println("El alumno " + alumno.getNombre() + " no tiene nota final para el curso " + idCurso);
            }


        }
     
        if(alumnosWrapper.isEmpty()) {
            return null;
        }
        
        AlumnoWrapper awEncontrado = quickSelect(alumnosWrapper, 0, alumnosWrapper.size() - 1, posicionK - 1);
        return awEncontrado.getAlumno();
    }
    
    public static AlumnoWrapper quickSelect(List<AlumnoWrapper> alumnos, int low, int high, int k) {
        if (low == high) {
            return alumnos.get(low);
        }

        int pivotIndex = partition(alumnos, low, high);
        if (k == pivotIndex) {
            return alumnos.get(k);
        } else if (k < pivotIndex) {
            return quickSelect(alumnos, low, pivotIndex - 1, k);
        } else {
            return quickSelect(alumnos, pivotIndex + 1, high, k);
        }
    }

    private static int partition(List<AlumnoWrapper> alumnos, int low, int high) {
        AlumnoWrapper pivot = alumnos.get(high);
        int i = low;

        for (int j = low; j < high; j++) {
            if (alumnos.get(j).getFinalScore() > pivot.getFinalScore()) {
                Collections.swap(alumnos, i, j);
                i++;
            }
        }
        Collections.swap(alumnos, i, high);
        return i;
    }
}

