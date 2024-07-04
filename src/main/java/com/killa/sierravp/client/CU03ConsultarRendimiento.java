/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.repository.Universidad;

/**
 *
 * @author USER
 */
public class CU03ConsultarRendimiento {
    
    private Universidad universidad;

    public CU03ConsultarRendimiento(Universidad universidad) {
        this.universidad = universidad;
    }
    
    public void consultarRendimiento(int idAlumno) {

        
        Alumno alumno = universidad.obtenerAlumnoPorId(idAlumno);

        if (alumno != null) {
            
            System.out.println("Alumno: " + alumno.getPrimerNombre() + " " + alumno.getSegundoNombre()+ " " + alumno.getPrimerApellido() + " " + alumno.getSegundoApellido());
            System.out.println("Posición en el ranking: " + alumno.getPosicionRanking());
            System.out.println("CRA Ponderado Actual: " + alumno.getCraPonderadoActual());
            //System.out.println("Histórico de CRA: " + alumno.getCraHistorico());
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }
}
