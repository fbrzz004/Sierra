package com.killa.sierravp.client;

import com.killa.sierravp.service.AlumnoService;
import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.service.CursoService;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AlumnoClient {
    private static CursoService cursoService = new CursoService();
    private static AlumnoService alumnoService = new AlumnoService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Ver estadísticas de la clase");
        System.out.println("2. Consultar Cursos y clases que lleva el alumno");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                //verEstadisticasClase();
                break;
            case 2:
                consultarRendimiento();
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    
    public static void verEstadisticasClase() {
        System.out.println("Ingrese el ID de la clase:");
        int idClase = scanner.nextInt();

        List<Nota> notas = cursoService.obtenerNotasPorClase(idClase);
        if (notas.isEmpty()) {
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
    
    //Se recuperan correctamente los cursos del alumno segun su id
    //OJO si tienen errores al momento de recuperar la informacion 
    //porque hibernate les dice lazy algo, tienen que adaptar su codigo a como esta el metodo de findByCodigo
    // Método para consultar el rendimiento de un alumno por su ID
    
    private static void consultarRendimiento() {
        System.out.print("Ingrese el ID del alumno: ");
        int id = scanner.nextInt();
        Alumno alumno = alumnoService.consultarRendimiento(id);

        if (alumno != null) {
            System.out.println("Nombre del alumno: " + alumno.getPrimerNombre());
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
                
                System.out.println("ID: " + alumno.getCodigo() + ", Nombre: " + alumno.getPrimerNombre() + alumno.getSegundoNombre()+ alumno.getPrimerApellido()+ alumno.getSegundoApellido() +", CRA Ponderado Actual: " + alumno.getCraPonderadoActual());
            }
        } else {
            
            System.out.println("No hay alumnos registrados.");
            
        }
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