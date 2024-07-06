package com.killa.sierravp.repository;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Curso;
import com.killa.sierravp.domain.EscuelaProfesional;
import com.killa.sierravp.domain.Facultad;
import com.killa.sierravp.domain.Profesor;
import com.killa.sierravp.domain.Usuario;

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

    public List<Usuario> obtenerUsuariosPorEscuela(String nombreFacultad, int idEscuela) {
        FacultadData facultad = obtenerFacultad(nombreFacultad);
        if (facultad != null) {
            EscuelaData escuela = facultad.obtenerEscuela(idEscuela);
            if (escuela != null) {
                List<Usuario> usuarios = new ArrayList<>();
                usuarios.addAll(escuela.getAlumnos());
                usuarios.addAll(escuela.getProfesores());
                return usuarios;
            }
        }
        return new ArrayList<>();  // Devuelve una lista vacía si no se encuentra la facultad o la escuela
    }

    public List<Usuario> obtenerUsuariosPorFacultad(String nombreFacultad) {
        FacultadData facultad = obtenerFacultad(nombreFacultad);
        if (facultad != null) {
            List<Usuario> usuarios = new ArrayList<>();
            for (EscuelaData escuela : facultad.getEscuelas().values()) {
                usuarios.addAll(escuela.getAlumnos());
                usuarios.addAll(escuela.getProfesores());
            }
            return usuarios;
        }
        return new ArrayList<>();  // Devuelve una lista vacía si no se encuentra la facultad o la escuela
    }

    public Alumno obtenerAlumnoPorId(int idAlumno) {
        Map<Integer, Alumno> mapaAlumnos = new HashMap<>();
        for (FacultadData facultad : facultades.values()) {
            for (EscuelaData escuela : facultad.getEscuelas().values()) {
                for (Alumno alumno : escuela.getAlumnos()) {
                    mapaAlumnos.put(alumno.getCodigo(), alumno);
                }
            }
        }
        System.out.println("Se busco entre -- > " + mapaAlumnos.size() + " alumnos");
        return mapaAlumnos.get(idAlumno);  // Devuelve null si no se encuentra el alumno
    }

    public Alumno obtenerAlumnoPorIdIngenuo(int idAlumno) {
        int c = 0;
        for (FacultadData facultad : facultades.values()) {
            for (EscuelaData escuela : facultad.getEscuelas().values()) {
                for (Alumno alumno : escuela.getAlumnos()) {
                    c++;
                    if (alumno.getCodigo() == idAlumno) {
                        System.out.println("Se busco entre -- > " + c + " alumnos");
                        return alumno;
                    }
                }
            }
        }
        return null;  // Devuelve null si no se encuentra el alumno
    }

    public Profesor obtenerProfesorPorId(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static class FacultadData {
        private String nombre;
        private int id;
        private Map<Integer, EscuelaData> escuelas;

        public FacultadData(String nombre) {
            this.nombre = nombre;
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
