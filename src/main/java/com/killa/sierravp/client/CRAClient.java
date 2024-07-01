package com.killa.sierravp.client;

import com.killa.sierravp.service.CRAService;

import java.util.Scanner;

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

    // Método main para ejecutar la aplicación cliente
    
    public static void main(String[] args) {
        calcularCRAporClase();
    }
}
