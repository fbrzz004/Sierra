/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.client;

import com.killa.sierravp.service.CRAService;
import java.util.Scanner;

/**
 *
 * @author USER
 */

public class CRAClient {
    private static CRAService craService = new CRAService();
    private static Scanner scan = new Scanner(System.in);

    // Método para calcular el CRA por clase
    
    public static void calcularCRAporClase() {
        
        // Solicitar al usuario que ingrese el ID de la clase
        
        System.out.println("Ingrese el ID de la clase: ");
        int claseId = scan.nextInt();

        // Llamar al servicio para calcular el CRA por clase
        
        craService.calcularCRAporClase(claseId);
    }

}
