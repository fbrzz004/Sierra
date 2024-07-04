package com.killa.sierravp.repository;

import com.killa.sierravp.domain.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Universidad {

    private Map<String, FacultadData> facultades;

    public Universidad() {
        this.facultades = new HashMap<>();
    }

    public void agregarFacultad(String nombreFacultad) {
        if (!facultades.containsKey(nombreFacultad)) {
            facultades.put(nombreFacultad, new FacultadData(nombreFacultad));
        }
    }

    public FacultadData obtenerFacultad(String nombreFacultad) {
        return facultades.get(nombreFacultad);
    }

    public Map<String, FacultadData> getFacultades() {
        return facultades;
    }

    public Alumno obtenerAlumnoPorId(int idAlumno) {
        for (FacultadData facultad : facultades.values()) {
            for (EscuelaData escuela : facultad.getEscuelas().values()) {
                for (Alumno alumno : escuela.getAlumnos()) {
                    if (alumno.getCodigo() == idAlumno) {
                        return alumno;
                    }
                }
            }
        }
        return null;
    }

    public Clase obtenerClasePorId(int claseId) {
        for (FacultadData facultadData : facultades.values()) {
            for (EscuelaData escuelaData : facultadData.getEscuelas().values()) {
                for (Clase clase : escuelaData.getClases()) {
                    if (clase.getId() == claseId) {
                        return clase;
                    }
                }
            }
        }
        return null;
    }

    // Método auxiliar para obtener un ciclo por su ID
    public Ciclo obtenerCicloPorId(int cicloId) {
        for (FacultadData facultadData : facultades.values()) {
            for (EscuelaData escuelaData : facultadData.getEscuelas().values()) {
                for (EscuelaProfesional escuelaProfesional : escuelaData.getEscuelasProfesionales()) {
                    for (Ciclo ciclo : escuelaProfesional.getCiclos()) {
                        if (ciclo.getId() == cicloId) {
                            return ciclo;
                        }
                    }
                }
            }
        }
        return null;
    }

    // Método auxiliar para obtener una Escuela Profesional por su ID
    public EscuelaProfesional obtenerEscuelaProfesionalPorId(int escuelaProfesionalId) {
        for (FacultadData facultadData : facultades.values()) {
            for (EscuelaData escuelaData : facultadData.getEscuelas().values()) {
                for (EscuelaProfesional escuelaProfesional : escuelaData.getEscuelasProfesionales()) {
                    if (escuelaProfesional.getId() == escuelaProfesionalId) {
                        return escuelaProfesional;
                    }
                }
            }
        }
        return null;
    }

    // Método auxiliar para obtener una facultad por su ID
    public FacultadData obtenerFacultadPorId(int facultadId) {
        for (FacultadData facultadData : facultades.values()) {
            if (facultadData.getId() == facultadId) {
                return facultadData;
            }
        }
        return null;
    }

    public static class FacultadData {

        private String nombre;
        private int id;
        private Map<Integer, EscuelaData> escuelas;

        public FacultadData(String nombre) {
            this.nombre = nombre;
            this.id = nombre.hashCode(); // Usar hashCode del nombre como ID único
            this.escuelas = new HashMap<>();
        }

        public int getId() {
            return id;
        }

        public void agregarEscuela(EscuelaProfesional escuela) {
            if (!escuelas.containsKey(escuela.getId())) {
                escuelas.put(escuela.getId(), new EscuelaData(escuela));
            }
        }

        public EscuelaData obtenerEscuela(int idEscuela) {
            return escuelas.get(idEscuela);
        }

        public Map<Integer, EscuelaData> getEscuelas() {
            return escuelas;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    }

    public static class EscuelaData {

        private EscuelaProfesional escuela;
        private List<Alumno> alumnos;
        private List<Profesor> profesores;
        private List<Curso> cursos;
        private List<Clase> clases;
        private List<EscuelaProfesional> escuelasProfesionales;

        public EscuelaData(EscuelaProfesional escuela) {
            this.escuela = escuela;
            this.alumnos = new ArrayList<>();
            this.profesores = new ArrayList<>();
            this.cursos = new ArrayList<>();
            this.clases = new ArrayList<>();
            this.escuelasProfesionales = new ArrayList<>();
        }

        public void agregarAlumno(Alumno alumno) {
            alumnos.add(alumno);
        }

        public void agregarProfesor(Profesor profesor) {
            profesores.add(profesor);
        }

        public void agregarCurso(Curso curso) {
            cursos.add(curso);
        }

        public void agregarClase(Clase clase) {
            clases.add(clase);
        }

        public void agregarEscuelaProfesional(EscuelaProfesional escuelaProfesional) {
            escuelasProfesionales.add(escuelaProfesional);
        }

        public List<Alumno> getAlumnos() {
            return alumnos;
        }

        public List<Profesor> getProfesores() {
            return profesores;
        }

        public List<Curso> getCursos() {
            return cursos;
        }

        public List<Clase> getClases() {
            return clases;
        }

        public List<EscuelaProfesional> getEscuelasProfesionales() {
            return escuelasProfesionales;
        }

        public EscuelaProfesional getEscuela() {
            return escuela;
        }

        public void setEscuela(EscuelaProfesional escuela) {
            this.escuela = escuela;
        }
    }
}
