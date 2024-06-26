package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.service.ProfesorService;
import com.killa.sierravp.util.TipoNota;

import java.util.List;
import java.util.Scanner;

public class ProfesorClient {
    private static ProfesorService profesorService = new ProfesorService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Ingresar notas");
        int opcion = scanner.nextInt();

        if (opcion == 1) {
            ingresarNotas();
        } else {
            System.out.println("Opción no válida");
        }
    }

    public static void ingresarNotas() {
        
        System.out.println("Ingrese el ID de la clase:");
        int idClase = scanner.nextInt();
        Clase clase = profesorService.findClaseByID(idClase); //aqui esta un problema
        //lo explico mejor en el repository

        if (clase == null) {
            System.out.println("Clase no encontrada.");
            return;
        }
        //esto esta muy bien la logica de recuperar alumnos
        List<Alumno> alumnos = profesorService.obtenerAlumnosPorClase(idClase);

        for (Alumno alumno : alumnos) {
            System.out.println("Ingresando notas para el alumno: " + alumno.getNombres());

            double notaParcial = leerNota("Nota parcial: "); //obtiene las notas mediante la consola 
            double notaFinal = leerNota("Nota final: ");
            double notaContinua = leerNota("Nota continua: ");

            if (notaParcial == -1 || notaFinal == -1 || notaContinua == -1) {
                System.out.println("Notas fuera del rango permitido. Ingrese nuevamente.");
                continue; //si hay error salta al siguiente alumno, en este caso deberias hacer que se insista hasta que ingrese valores correctos
            }

            boolean exitoParcial = guardarNota(notaParcial, TipoNota.EP, alumno, clase);
            boolean exitoFinal = guardarNota(notaFinal, TipoNota.EF, alumno, clase);
            boolean exitoContinua = guardarNota(notaContinua, TipoNota.EC, alumno, clase);

            if (exitoParcial && exitoFinal && exitoContinua) {
                System.out.println("Notas guardadas exitosamente.");
            } else { //aqui podrias indicar que nota fallo usando swich 
                System.out.println("Error al guardar las notas.");
            }
        }
    }

    private static double leerNota(String mensaje) {
        System.out.print(mensaje);
        double nota = scanner.nextDouble();
        if (nota < 0 || nota > 20) {
            return -1;
        }
        return nota;
    }

    private static boolean guardarNota(double calificacion, TipoNota tipo, Alumno alumno, Clase clase) {
        Nota nota = new Nota();
        nota.setCalificacion((int) calificacion);
        nota.setTipo(tipo);
        nota.setAlumno(alumno);
        nota.setClase(clase);
        nota.setCurso(clase.getCurso());

        return profesorService.guardarNota(nota);
    }
}