package com.killa.sierravp.client;

import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.service.ClaseService;
import java.util.Scanner;

public class ClaseClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClaseService claseService = new ClaseService();

        System.out.println("Ingrese el ID de la clase para consultar:");
        int id = scanner.nextInt();

        Clase clase = claseService.getClaseById(id);
        if (clase != null) {
            System.out.println("Información de la clase:");
            System.out.println("ID: " + clase.getId());
            System.out.println("Curso: " + clase.getCurso().getNombre());
            System.out.println("Profesor: " + clase.getProfesor().getNombres() + " " + clase.getProfesor().getApellidos());
            System.out.println("Alumnos inscritos: ");
            clase.getAlumnos().forEach(alumno -> {
                System.out.println(alumno.getNombres() + " " + alumno.getApellidos());
            });
        } else {
            System.out.println("No se encontró la clase con el ID proporcionado.");
        }

        claseService.close();
    }
}
