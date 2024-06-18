/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.client;

import com.killa.sierravp.service.AlumnoService;
import com.killa.sierravp.domain.Alumno;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author karlo
 */

public class AlumnoClient {

    private static AlumnoService alumnoService = new AlumnoService();
    private static Scanner scan = new Scanner(System.in);

    // Muestra un menú con opciones y procesa la entrada del usuario.
     
    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenu de Opciones:");
            System.out.println("1. Consultar rendimiento de un alumno");
            System.out.println("2. Mostrar todos los alumnos");
            System.out.println("3. Actualizar datos de un alumno");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opción: ");
            int opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    consultarRendimiento();
                    break;
                case 2:
                    mostrarTodosLosAlumnos();
                    break;
                case 3:
                    actualizarAlumno();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida, por favor intente de nuevo.");
                    break;
            }
        }
        scan.close();
    }

    //Método para consultar el rendimiento de un alumno específico por ID.
     
    public static void consultarRendimiento() {
        System.out.print("Ingrese el ID del estudiante: ");
        int id = scan.nextInt();
        Alumno alumno = alumnoService.consultarRendimiento(id);
        if (alumno != null) {
            System.out.println("Alumno: " + alumno.getNombre());
            System.out.println("Posición en el ranking: " + alumno.getPosicionRanking());
            System.out.println("CRA Ponderado Actual: " + alumno.getCraPonderadoActual());
            System.out.println("Histórico de CRA: " + alumno.getCraHistorico());
        } else {
            System.out.println("No se pudo recuperar la información de rendimiento.");
        }
    }

    //Método para mostrar todos los alumnos registrados en el sistema.
     
    public static void mostrarTodosLosAlumnos() {
        List<Alumno> alumnos = alumnoService.obtenerTodosLosAlumnos();
        if (alumnos != null && !alumnos.isEmpty()) {
            for (Alumno alumno : alumnos) {
                System.out.println(alumno);
            }
        } else {
            System.out.println("No hay alumnos registrados.");
        }
    }

    //Método para actualizar los datos de un alumno existente.
     
    public static void actualizarAlumno() {
        System.out.print("Ingrese el ID del alumno a actualizar: ");
        int id = scan.nextInt();
        Alumno alumno = alumnoService.consultarRendimiento(id);
        if (alumno != null) {
            System.out.print("Ingrese el nuevo CRA Ponderado Actual: ");
            double cra = scan.nextDouble();
            alumno.setCraPonderadoActual(cra); // Asumiendo que hay un setter para esto
            boolean exito = alumnoService.actualizarAlumno(alumno);
            if (exito) {
                System.out.println("Alumno actualizado exitosamente.");
            } else {
                System.out.println("Error al actualizar al alumno.");
            }
        } else {
            System.out.println("No se encontró al alumno.");
        }
    }
}