package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.repository.Universidad;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AlumnoService {
    private Universidad universidad;

    // Constructor que inicializa la clase AlumnoService con un objeto Universidad
    
    public AlumnoService(Universidad universidad) {
        this.universidad = universidad;
    }

    // Método para consultar el rendimiento de un alumno por su ID y nombre de la facultad
    
    public Alumno consultarRendimiento(int codigo, String nombreFacultad) {
        HashMap<Integer,Alumno> map = new HashMap<>(2000);
        List<Alumno> allAlumnos = todosLosAlumnosDeFacultad(nombreFacultad);
        for (Alumno alumno : allAlumnos) {
            map.put(alumno.getCodigo(), alumno);
        }
        return map.get(codigo);
    }

    // Método para obtener todos los alumnos en un formato de lista (ID -> Alumno)
    
    public List<Alumno> obtenerTodosLosAlumnos(String nombreFacultad) {
        return todosLosAlumnosDeFacultad(nombreFacultad).stream()
                .collect(Collectors.toList());
    }

    // Método para obtener todos los alumnos de una facultad específica
    
    public List<Alumno> todosLosAlumnosDeFacultad(String nombreFacultad) {
        Universidad.FacultadData facultadData = universidad.obtenerFacultad(nombreFacultad);
        Collection<Universidad.EscuelaData> escuelasData = facultadData.getEscuelas().values();
        List<Alumno> allAlumnos = new LinkedList<>();
        for (Universidad.EscuelaData ep : escuelasData) {
            allAlumnos.addAll(ep.getAlumnos());
        }
        return allAlumnos;
    }
    
    private void calcularPosicionRanking(Alumno alumno, List<Alumno> alumnos) {
        List<Alumno> alumnosOrdenados = alumnos.stream()
                .sorted((a1, a2) -> Double.compare(a2.getCraPonderadoActual(), a1.getCraPonderadoActual()))
                .collect(Collectors.toList());

        int posicion = alumnosOrdenados.indexOf(alumno) + 1;
        alumno.setPosicionRanking(posicion);
    }
}
