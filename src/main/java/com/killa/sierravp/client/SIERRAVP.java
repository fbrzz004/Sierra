/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.killa.sierravp.client;

/**
 *
 * @author karlo
 */

import com.killa.sierravp.service.AlumnoService;
import com.killa.sierravp.service.CRAService;
import com.killa.sierravp.domain.Alumno;
import java.util.Scanner;

public class SIERRAVP {
    private static AlumnoService alumnoService = new AlumnoService();
    private static CRAService craService = new CRAService();
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Consultar información de rendimiento");
            System.out.println("2. Calcular CRA por clase");
            System.out.println("3. Salir");

            int option = scan.nextInt();

            switch (option) {
                case 1:
                    consultarRendimiento();
                    break;
                case 2:
                    calcularCRAporClase();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    private static void consultarRendimiento() {
        System.out.println("Ingrese su ID de estudiante: ");
        int id = scan.nextInt();

        Alumno alumno = alumnoService.consultarRendimiento(id);

        if (alumno != null) {
            System.out.println("Posición en el ranking: " + alumno.getPosicionRanking());
            System.out.println("CRA Ponderado Actual: " + alumno.getCraPonderadoActual());
            System.out.println("Histórico de CRA: " + alumno.getCraHistoricoValues());
        } else {
            System.out.println("No se pudo recuperar la información de rendimiento.");
        }
    }

    private static void calcularCRAporClase() {
        System.out.println("Ingrese el ID de la clase: ");
        int claseId = scan.nextInt();

        craService.calcularCRAporClase(claseId);
        System.out.println("CRA calculado para la clase con ID " + claseId);
    }
}
