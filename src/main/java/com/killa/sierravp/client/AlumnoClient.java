package com.killa.sierravp.client;

import com.killa.sierravp.service.AlumnoService;
import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.service.CursoService;
import com.killa.sierravp.repository.Universidad;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AlumnoClient {
    private static Universidad universidad = new Universidad();
    private static AlumnoService alumnoService = new AlumnoService(universidad);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        System.out.println(" Consultar Cursos y clases que lleva el alumno: ");
        System.out.println(" 1. SI - 2. NO ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                consultarRendimiento();
                break;
            case 2:
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    // Método para consultar el rendimiento de un alumno por su ID
    private static void consultarRendimiento() {
        System.out.print("Ingrese el código del alumno: ");
        int codigo = scanner.nextInt();
        Alumno alumno = alumnoService.consultarRendimiento(codigo);

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
        Map<Integer, Alumno> alumnos = (Map<Integer, Alumno>) alumnoService.obtenerTodosLosAlumnos();

        if (alumnos != null && !alumnos.isEmpty()) {
            System.out.println("Lista de todos los alumnos:");
            for (Map.Entry<Integer, Alumno> entry : alumnos.entrySet()) {
                Alumno alumno = entry.getValue();
                System.out.println("ID: " + alumno.getCodigo() + ", Nombre: " + alumno.getPrimerNombre() + " " + alumno.getSegundoNombre() + " " + alumno.getPrimerApellido() + " " + alumno.getSegundoApellido() + ", CRA Ponderado Actual: " + alumno.getCraPonderadoActual());
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
