package com.killa.sierravp.client;

import com.killa.sierravp.domain.Clase;
import java.util.Scanner;

public class ClaseClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el ID de la clase para consultar:");
        int id = scanner.nextInt();

        //Clase clase = claseService.getClaseById(id); que ahora recupere de la memoria 
        Clase clase = null; //de momento en lo que se implementa
        if (clase != null) {
            System.out.println("Información de la clase:");
            System.out.println("ID: " + clase.getId());
            System.out.println("Curso: " + clase.getCurso().getNombre());
            System.out.println("Profesor: " + clase.getProfesor().getPrimerNombre() + " " + clase.getProfesor().getPrimerApellido());
            System.out.println("Alumnos inscritos: ");
            clase.getAlumnos().forEach(alumno -> {
                System.out.println(alumno.getPrimerNombre()+ " " + alumno.getPrimerApellido());
            });
        } else {
            System.out.println("No se encontró la clase con el ID proporcionado.");
        }
    }
}
