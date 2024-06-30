package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Curso;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.service.AlumnoService;
import com.killa.sierravp.service.ClaseService;
import com.killa.sierravp.service.CursoService;
import com.killa.sierravp.service.ProfesorService;
import com.killa.sierravp.util.TipoNota;

import java.util.List;
import java.util.Scanner;

public class ProfesorClient {
    private static ProfesorService profesorService = new ProfesorService();
    private static ClaseService claseService = new ClaseService();
    private static AlumnoService alumnoService = new AlumnoService();
    private static CursoService cursoService = new CursoService();
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
        //mostrarle la lista de clases que el profesor dicta -> mediante el codigo del profesir
        //obtengo el id de clase
        
        System.out.println("Ingrese el código del profesor:");
        int codigoProfesor = scanner.nextInt();
        List<Clase> clases = claseService.getClasesByProfesorId(codigoProfesor);
        if (clases.isEmpty()) {
           System.out.println("El profesor no tiene clases disponibles.");
           return;
        }
        
        System.out.println("Clases disponibles:");
         clases.forEach(clase -> {
             System.out.println(String.format("Clase con id [%s]", clase.getId()));
         });
        
        System.out.println("Ingrese el código de la clase:");
        int codigoClase = scanner.nextInt();

        List<Alumno> alumnos = profesorService.obtenerAlumnosPorClase(codigoClase);

        for (Alumno alumno : alumnos) {
            System.out.println("Ingresando notas para el alumno: " + alumno.getCorreo());

            double notaParcial = leerNota("Nota parcial: "); //obtiene las notas mediante la consola 
            double notaFinal = leerNota("Nota final: ");
            double notaContinua = leerNota("Nota continua: ");

            Clase clase = claseService.getClaseById(codigoClase);
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
        double nota;

        do {
            System.out.print(mensaje);
            nota = scanner.nextDouble();
        } while(nota < 0 || nota > 20);
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