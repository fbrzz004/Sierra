package com.killa.sierravp.client;

import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.service.ClaseService;
import com.killa.sierravp.repository.Universidad;

import java.util.Scanner;

public class ClaseClient {

    private static ClaseService claseService;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // inicializar Universidad
        Universidad universidad = GenerarFacultades.GenerarFacultadesCompletas("Facultad de Ciencias Físicas");
        
        claseService = new ClaseService(universidad);

        System.out.println("Seleccione una opción:");
        System.out.println("1. Consultar información de la clase");
        System.out.println("2. Ver estadísticas de la clase");
        int opcion = scanner.nextInt();

        System.out.println("Ingrese el ID de la clase para consultar:");
        int id = scanner.nextInt();

        if (opcion == 1) {
            Clase clase = claseService.getClaseById(id);
            if (clase != null) {
                System.out.println("Información de la clase:");
                System.out.println("ID: " + clase.getId());
                System.out.println("Curso: " + clase.getCurso().getNombre());
                System.out.println("Profesor: " + clase.getProfesor().getPrimerNombre() + " " + clase.getProfesor().getPrimerApellido());
                System.out.println("Alumnos inscritos: ");
                clase.getAlumnos().forEach(alumno -> {
                    System.out.println(alumno.getPrimerNombre() + " " + alumno.getPrimerApellido());
                });
            } else {
                System.out.println("No se encontró la clase con el ID proporcionado.");
            }
        } else if (opcion == 2) {
            String estadisticas = claseService.obtenerEstadisticasDeClase(id);
            System.out.println(estadisticas);
        } else {
            System.out.println("Opción no válida. Intente nuevamente.");
        }
    }
}
