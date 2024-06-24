/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.service;

import com.killa.sierravp.domain.InteresesAcademicos;
import com.killa.sierravp.repository.InteresesAcademyRepository;

/**
 *
 * @author karlo
 */
public class InteresesAcademicosService {
    InteresesAcademyRepository iar = new InteresesAcademyRepository();
    
    public InteresesAcademicos BuscarIntAcdByCod(int cod) {
        return iar.obtenerIntAcdAlumno(cod);
    }

    public float similitudCoseno(int idAlumno1, int idAlumno2) {
        InteresesAcademicos alumno1Intereses = BuscarIntAcdByCod(idAlumno1);
        InteresesAcademicos alumno2Intereses = BuscarIntAcdByCod(idAlumno2);

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
