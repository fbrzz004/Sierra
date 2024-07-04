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
    private static String nombreFacultad = "Facultad de Ciencias Físicas"; // Nombre de la facultad OJO se debe mandar como parametro

    public static void main(String[] args) {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Ver estadísticas de la clase");
        System.out.println("2. Consultar rendimiento del alumno");
        System.out.println("3. Buscar recomendaciones de compañeros");
        int opcion = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        Universidad universidad = GenerarFacultades.GenerarFacultadesCompletas(nombreFacultad);
        CU03ConsultarRendimiento consultarrendimiento = new CU03ConsultarRendimiento(universidad);
        CU07RecomendacionCompañeros recomendacionCompañeros = new CU07RecomendacionCompañeros(universidad);
        
        switch (opcion) {
            case 1:
                verEstadisticasClase(universidad);
                break;
            case 2:
                consultarRendimiento(consultarrendimiento);
                break;
            case 3:               
                Alumno alumnoBusca = universidad.obtenerAlumnoPorId(400); // cuando hagas el login tienes que mandar a este metodo al estudiante que se logeo
                if (alumnoBusca==null) {
                    System.out.println("no se estan generando alumnos");
                }
                buscarRecomendacionesCompaneros(recomendacionCompañeros,alumnoBusca);
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

    public static void consultarRendimiento(CU03ConsultarRendimiento consultarrendimiento) {
        System.out.println("Ingrese el ID del alumno:");
        int codigoAlumno = scanner.nextInt();
        scanner.nextLine(); 

        consultarrendimiento.consultarRendimiento(codigoAlumno);
    }

    public static void buscarRecomendacionesCompaneros(CU07RecomendacionCompañeros recomendacionCompañeros,Alumno alumnoBusca) {
    //aqui se busca el id de una ep en base a su nombre que debo recuperar del alumno
    //logeado cuando usaba fisica funcionaba piolin id 103
        recomendacionCompañeros.RecomendarCompañeros(alumnoBusca);
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
