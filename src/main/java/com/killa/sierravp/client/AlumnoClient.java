package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.repository.Universidad;
import com.killa.sierravp.service.CursoService;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AlumnoClient {

    private static Scanner scanner = new Scanner(System.in);

    private static String nombreFacultad = "Facultad de Derecho y Ciencia Política"; // Nombre de la facultad Facultad de Ciencias Fisicas

    public static void main(String[] args) {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Ver estadísticas de la clase");
        System.out.println("2. Consultar rendimiento del alumno");
        System.out.println("3. Buscar recomendaciones de compañeros");
        int opcion = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        Universidad universidad = GenerarFacultades.GenerarFacultadesCompletas(nombreFacultad);
        CU03yCU05ConsultarRendimiento consultarrendimiento = new CU03yCU05ConsultarRendimiento(universidad);
        CU07RecomendacionCompañerosMejorado recomendacionCompañeros = new CU07RecomendacionCompañerosMejorado(universidad);

        switch (opcion) {
            case 1:
                verEstadisticasClase(universidad);
                break;
            case 2:
                consultarRendimiento(consultarrendimiento);
                break;
            case 3:
                Alumno alumno = universidad.obtenerAlumnoPorId(500); //aqui debe ir el id del alummno que se logeeo
                //o mejor lo pasas como parametro, lo envio asi para poder probrar nomas
                recomendacionCompañeros.RecomendarCompañeros(alumno);
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    public static void verEstadisticasClase(Universidad universidad) {
        System.out.println("Ingrese el ID de la clase:");
        int idClase = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        CursoService cursoService = new CursoService(universidad);
        List<Nota> notas = null;  // Aquí deberías obtener las notas de la clase desde la memoria

        if (notas == null || notas.isEmpty()) {
            System.out.println("No se encontraron notas para la clase con ID " + idClase);
            return;
        }

        double media = cursoService.calcularMedia(notas);
        int notaMinima = cursoService.obtenerNotaMinima(notas);
        int notaMaxima = cursoService.obtenerNotaMaxima(notas);

        System.out.println("Estadísticas de la clase:");
        System.out.println("Media de notas: " + media);
        System.out.println("Nota mínima: " + notaMinima);
        System.out.println("Nota máxima: " + notaMaxima);
    }

    public static void consultarRendimiento(CU03yCU05ConsultarRendimiento consultarrendimiento) {
        System.out.println("Ingrese el código del alumno ( > = 300):");
        int codigoAlumno = scanner.nextInt();
        scanner.nextLine(); 

        consultarrendimiento.consultarRendimiento(codigoAlumno);
        
    }

    public static void mostrarInfoAlumno(Clase clase) {
        StringBuilder sb = new StringBuilder();
        sb.append(clase.getCurso().getNombre()).append(" y su id de curso es ").append(clase.getCurso().getId());
        Set<Clase> clases = clase.getCurso().getClases();
        System.out.println(sb.toString());
        System.out.println("Todas las clases de ese curso son ");
        for (Clase clase1 : clases) {
            System.out.println("Id de la clase " + clase1.getId());
        }
    }
}