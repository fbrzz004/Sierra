package com.killa.sierravp.client;

import java.util.Scanner;

public class SIERRAVP {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al Sistema Integral de Evaluación del Rendimiento Académico y Formación de Vínculos Profesionales (SIERRAVP)");
        
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Consultar Clase por ID");
            System.out.println("2. Salir");
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    ClaseClient.main(args);
                    break;
                case 2:
                    System.out.println("Saliendo del sistema.");
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
