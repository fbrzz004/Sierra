/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.InteresesAcademicos;

/**
 *
 * @author karlo
 */
public class InteresesAcademicosService {

    public float similitudCoseno(Alumno a1, Alumno a2) {
        InteresesAcademicos alumno1Intereses = a1.getInteresesAcademicos();
        InteresesAcademicos alumno2Intereses = a2.getInteresesAcademicos();

        // Calcular el producto punto
        float productoPunto = (alumno1Intereses.getInvestigacion() * alumno2Intereses.getInvestigacion()) +
                              (alumno1Intereses.getTrabajoEmpresarial() * alumno2Intereses.getTrabajoEmpresarial()) +
                              (alumno1Intereses.getEmprendimiento() * alumno2Intereses.getEmprendimiento()) +
                              (alumno1Intereses.getVoluntariado() * alumno2Intereses.getVoluntariado());

        // Calcular las normas
        float normaAlumno1 = (float) Math.sqrt(
                Math.pow(alumno1Intereses.getInvestigacion(), 2) +
                Math.pow(alumno1Intereses.getTrabajoEmpresarial(), 2) +
                Math.pow(alumno1Intereses.getEmprendimiento(), 2) +
                Math.pow(alumno1Intereses.getVoluntariado(), 2)
        );

        float normaAlumno2 = (float) Math.sqrt(
                Math.pow(alumno2Intereses.getInvestigacion(), 2) +
                Math.pow(alumno2Intereses.getTrabajoEmpresarial(), 2) +
                Math.pow(alumno2Intereses.getEmprendimiento(), 2) +
                Math.pow(alumno2Intereses.getVoluntariado(), 2)
        );

        // Calcular la similitud coseno
        return productoPunto / (normaAlumno1 * normaAlumno2);
    }
}
