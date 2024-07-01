package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.service.AlumnoService;

import java.util.Map;
import java.util.Scanner;

public class AlumnoClient {
    private static AlumnoService alumnoService = new AlumnoService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenú de Opciones:");
            System.out.println("1. Consultar rendimiento de un alumno por ID");
            System.out.println("2. Obtener todos los alumnos");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (option) {
                case 1:
                    consultarRendimiento();
                    break;
                case 2:
                    obtenerTodosLosAlumnos();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
        scanner.close();
    }

    // Método para consultar el rendimiento de un alumno por su ID
    
    private static void consultarRendimiento() {
        System.out.print("Ingrese el ID del alumno: ");
        int id = scanner.nextInt();
        Alumno alumno = alumnoService.consultarRendimiento(id);

        if (alumno != null) {
            System.out.println("Nombre del alumno: " + alumno.getNombre());
            System.out.println("CRA Ponderado Actual: " + alumno.getCraPonderadoActual());
            System.out.println("Posición en el ranking: " + alumno.getPosicionRanking());
            
        } else {
            System.out.println("No se encontró un alumno con el ID proporcionado.");
        }
    }

    // Método para obtener todos los alumnos
    
    private static void obtenerTodosLosAlumnos() {
        
        Map<Integer, Alumno> alumnos = alumnoService.obtenerTodosLosAlumnos();

        if (alumnos != null && !alumnos.isEmpty()) {
            
            System.out.println("Lista de todos los alumnos:");
            
            for (Map.Entry<Integer, Alumno> entry : alumnos.entrySet()) {
                
                Alumno alumno = entry.getValue();
                
                System.out.println("ID: " + alumno.getCodigo() + ", Nombre: " + alumno.getNombre() + ", CRA Ponderado Actual: " + alumno.getCraPonderadoActual());
            }
        } else {
            
            System.out.println("No hay alumnos registrados.");
            
        }
    }
}
