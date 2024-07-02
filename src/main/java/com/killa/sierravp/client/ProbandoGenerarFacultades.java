/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Profesor;
import com.killa.sierravp.repository.Universidad;
import java.util.Map;

/**
 *
 * @author HITV
 */
public class ProbandoGenerarFacultades {


    public static void main(String[] args) {
        String nombreFacultad = "Facultad de Ciencias Físicas";
        
        // Generar la universidad con la facultad completa
        Universidad universidad = GenerarFacultades.GenerarFacultadesCompletas(nombreFacultad);
        
        // Obtener la facultad
        Universidad.FacultadData facultadData = universidad.obtenerFacultad(nombreFacultad);

        // Mostrar todas las escuelas profesionales de la facultad
        System.out.println("Escuelas Profesionales en la " + nombreFacultad + ":");
        for (Universidad.EscuelaData escuelaData : facultadData.getEscuelas().values()) {
            System.out.println(escuelaData.getEscuela().getNombre());
        }

        // Mostrar todos los alumnos del ciclo 5 de la escuela de Física
        System.out.println("\nAlumnos de la escuela de Física en el ciclo 5:");
        Universidad.EscuelaData escuelaFisica = facultadData.obtenerEscuela(103); // Asumiendo que Física tiene el ID 1
        if (escuelaFisica != null) {
            for (Alumno alumno : escuelaFisica.getAlumnos()) {
                if (alumno.getCiclo() == 5) {
                    System.out.println(alumno.getPrimerNombre() + " " + alumno.getPrimerApellido());
                }
            }
        } else {
            System.out.println("No se encontró la escuela de Física.");
        }

        // Mostrar todos los profesores
        System.out.println("\nProfesores de la Facultad:");
        for (Universidad.EscuelaData escuelaData : facultadData.getEscuelas().values()) {
            for (Profesor profesor : escuelaData.getProfesores()) {
                System.out.println(profesor.getPrimerNombre() + " " + profesor.getPrimerApellido());
            }
        }
    }
}

