package com.killa.sierravp.client;

import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.service.CursoService;
import com.killa.sierravp.service.AlumnoService;
import com.killa.sierravp.domain.Alumno;
import java.util.List;
import java.util.Scanner;

public class AlumnoClient {
    private static CursoService cursoService = new CursoService();
    private static AlumnoService alumnoService = new AlumnoService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Ver estadísticas de la clase");
        System.out.println("2. Consultar rendimiento del alumno");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                verEstadisticasClase();
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

    public static void consultarRendimiento() {
        System.out.println("Ingrese su ID de estudiante: ");
        int id = scanner.nextInt();

        Alumno alumno = alumnoService.findByID(id);

        if (alumno != null) {
            System.out.println("Posición en el ranking: " + alumno.getPosicionRanking());
            System.out.println("CRA Ponderado Actual: " + alumno.getCraPonderadoActual());
            System.out.println("Histórico de CRA: " + alumno.getCraHistorico());
        } else {
            System.out.println("No se pudo recuperar la información de rendimiento.");
        }
    }
}
