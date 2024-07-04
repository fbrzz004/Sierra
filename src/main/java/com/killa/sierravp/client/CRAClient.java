package com.killa.sierravp.client;

import com.killa.sierravp.repository.Universidad;
import com.killa.sierravp.service.CRAService;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.CRA;

import java.util.Scanner;

public class CRAClient {
    private static Universidad universidad = new Universidad();
    private static CRAService craService = new CRAService(universidad);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        System.out.println("Seleccione una opción:");
        System.out.println("1. Calcular y registrar CRA de una clase");
        System.out.println("2. Ver todos los CRAs");
        System.out.println("3. Buscar CRA por ID");
        
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                calcularYRegistrarCRA();
                break;
            case 2:
                verTodosLosCRAs();
                break;
            case 3:
                buscarCRAporId();
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    private static void calcularYRegistrarCRA() {
        System.out.println("Ingrese el ID de la clase:");
        int idClase = scanner.nextInt();

        // Supongamos que tienes un método en CRAService para obtener la clase por ID
        Clase clase = obtenerClasePorId(idClase);  // Implementa este método según tu lógica
        if (clase != null) {
            craService.calcularYRegistrarCRA(clase);
            System.out.println("CRA calculado y registrado para la clase con ID " + idClase);
        } else {
            System.out.println("No se encontró una clase con el ID proporcionado.");
        }
    }

    private static void verTodosLosCRAs() {
        System.out.println("Todos los CRAs:");
        craService.getAllCRAs().forEach(cra -> 
            System.out.println("CRA ID: " + cra.getCraId() + ", Valor: " + cra.getCra()));
    }

    private static void buscarCRAporId() {
        System.out.print("Ingrese el ID del CRA: ");
        int craId = scanner.nextInt();
        CRA cra = craService.getCRAById(craId);

        if (cra != null) {
            System.out.println("CRA encontrado: Valor = " + cra.getCra());
        } else {
            System.out.println("No se encontró un CRA con el ID proporcionado.");
        }
    }

    // Implementa este método para obtener la clase por ID, según tu lógica
    private static Clase obtenerClasePorId(int idClase) {
        // Ejemplo de implementación:
        // return universidad.getFacultades().values().stream()
        //         .flatMap(facultad -> facultad.getEscuelas().values().stream())
        //         .flatMap(escuela -> escuela.getClases().stream())
        //         .filter(clase -> clase.getId() == idClase)
        //         .findFirst()
        //         .orElse(null);
        return null; // Reemplaza con tu lógica
    }
}
