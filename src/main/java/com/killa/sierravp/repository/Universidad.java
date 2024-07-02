/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.repository;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import java.util.ArrayList;
import com.killa.sierravp.domain.EscuelaProfesional;
import com.killa.sierravp.domain.Profesor;
import java.util.HashMap;
import com.killa.sierravp.domain.Curso;
import com.killa.sierravp.domain.Usuario;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HITV
 */
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

            for (EscuelaData escuela : facultad.getEscuelas().values()) {
                if (escuela != null) {
                    List<Usuario> usuarios = new ArrayList<>();
                    usuarios.addAll(escuela.getAlumnos());
                    usuarios.addAll(escuela.getProfesores());
                    return usuarios;
                }
            }

        }
        return new ArrayList<>();  // Devuelve una lista vacía si no se encuentra la facultad o la escuela
    }

    public static class FacultadData {

        private String nombre;
        private Map<Integer, EscuelaData> escuelas;

        public FacultadData(String nombre) {
            this.nombre = nombre;
            this.escuelas = new HashMap<>();
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

        public EscuelaData(EscuelaProfesional escuela) {
            this.escuela = escuela;
            this.alumnos = new ArrayList<>();
            this.profesores = new ArrayList<>();
            this.cursos = new ArrayList<>();
            this.clases = new ArrayList<>();
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

        public EscuelaProfesional getEscuela() {
            return escuela;
        }

        public void setEscuela(EscuelaProfesional escuela) {
            this.escuela = escuela;
        }
    }
}
