/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.repository.Universidad;
import java.util.Collection;

import java.util.LinkedList;

import java.util.List;

public class AlumnoService {

    public AlumnoService() {
        
    }

    public List<Alumno> todosLosAlumnosDeFacultad(String nombreFacultad, Universidad universidad) {
        Universidad.FacultadData facultadData = universidad.obtenerFacultad(nombreFacultad);
        Collection<Universidad.EscuelaData> escuelasData = facultadData.getEscuelas().values();
        List<Alumno> allAlumnos = new LinkedList<>();
        for (Universidad.EscuelaData ep : escuelasData) {
            allAlumnos.addAll(ep.getAlumnos());
        }
        return allAlumnos;
    }

}
