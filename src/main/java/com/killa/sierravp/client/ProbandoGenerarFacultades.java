/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Profesor;
import com.killa.sierravp.repository.Universidad;


/**
 *
 * @author HITV
 */
public class ProbandoGenerarFacultades {

    public static void main(String[] args) {

        String nombreFacultad = "Facultad de Medicina Veterinaria";

        // Generar la universidad con la facultad completa
        Universidad universidad = GenerarFacultades.GenerarFacultadesCompletas(nombreFacultad);

        // Obtener la facultad
        Universidad.FacultadData facultadData = universidad.obtenerFacultad(nombreFacultad);

        // Verificar asignaciones de cursos a alumnos
        System.out.println("\nVerificando asignaciones de cursos a alumnos:");
        for (Universidad.EscuelaData escuelaData : facultadData.getEscuelas().values()) {
            for (Alumno alumno : escuelaData.getAlumnos()) {
                System.out.println("Alumno: " + alumno.getPrimerNombre() + " " + alumno.getPrimerApellido() + " - Ciclo: " + alumno.getCiclo());
                for (Clase clase : alumno.getClases()) {
                    System.out.println("    Curso: " + clase.getCurso().getNombre());
                    System.out.println("Profesor que dicta el curso: " + clase.getProfesor().getPrimerNombre()+" "+clase.getProfesor().getPrimerApellido());
                }
            }
        }

        // Verificar asignaciones de cursos a profesores
        System.out.println("\nVerificando asignaciones de cursos a profesores:");
        for (Universidad.EscuelaData escuelaData : facultadData.getEscuelas().values()) {
            for (Profesor profesor : escuelaData.getProfesores()) {
                System.out.println("Profesor: " + profesor.getPrimerNombre() + " " + profesor.getPrimerApellido());
                if (profesor.getClases() != null) {
                    for (Clase clase :  profesor.getClases()) {
                        System.out.println("    Curso que dicta: " + clase.getCurso().getNombre());
                    }
                } else {
                    System.out.println("    No tiene cursos asignados.");
                }
            }
        }

    }
}
