// CU03yCU05ConsultarRendimiento.java
package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.repository.Universidad;
import com.killa.sierravp.service.CRAService;

public class CU03yCU05ConsultarRendimiento {
    
    private Universidad universidad;
    private CRAService craService;

    public CU03yCU05ConsultarRendimiento(Universidad universidad) {
        this.universidad = universidad;
        this.craService = new CRAService(universidad);
    }
    
    public void consultarRendimiento(int codigoAlumno) {
        System.out.println("Consultar rendimiento del alumno");
        System.out.println();

        // Calcular el CRA del alumno
        craService.calcularCRAPorAlumno(codigoAlumno);

        // Mostrar las notas del alumno
        craService.mostrarNotasAlumno(codigoAlumno);

        // Obtener el alumno y mostrar su CRA ponderado actual
        Alumno alumno = universidad.obtenerAlumnoPorId(codigoAlumno);
        
        if (alumno != null) {
            System.out.println();
            System.out.println("CRA Ponderado Actual: " + alumno.getCraPonderadoActual());
        } else {
            System.out.println();
            System.out.println("No se encontró un alumno con el código proporcionado.");
        }
    }
}
