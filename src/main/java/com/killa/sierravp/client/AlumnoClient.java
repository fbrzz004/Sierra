package com.killa.sierravp.client;

import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.service.CursoService;

import java.util.List;
import java.util.Scanner;

public class AlumnoClient {
    private static CursoService cursoService = new CursoService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        verEstadisticasClase();
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
}
