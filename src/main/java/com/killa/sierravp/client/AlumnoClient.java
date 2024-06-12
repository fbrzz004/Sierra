/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.client;

import com.killa.sierravp.service.AlumnoService;
import com.killa.sierravp.domain.Alumno;
import java.util.Scanner;
/**
 *
 * @author karlo
 */

public class AlumnoClient {
    
    // Crear una instancia del servicio de alumno
    
    private static AlumnoService alumnoService = new AlumnoService();
    private static Scanner scan = new Scanner(System.in);

    // Método para consultar el rendimiento de un alumno
    
    public static void consultarRendimiento() {
        
        System.out.println("Ingrese su ID de estudiante: ");
        int id = scan.nextInt();

        // Llamar al servicio para consultar el rendimiento del alumno
        
        Alumno alumno = alumnoService.consultarRendimiento(id);

        // Mostrar los resultados al usuario
        
        if (alumno != null) {
            System.out.println("Posición en el ranking: " + alumno.getPosicionRanking());
            System.out.println("CRA Ponderado Actual: " + alumno.getCraPonderadoActual());
            System.out.println("Histórico de CRA: " + alumno.getCraHistorico());
        } else {
            System.out.println("No se pudo recuperar la información de rendimiento.");
        }
    }

}