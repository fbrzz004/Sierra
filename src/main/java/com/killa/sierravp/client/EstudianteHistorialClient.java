package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.service.NotaService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class EstudianteHistorialClient {

    private static NotaService notaService = new NotaService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Visualizar historial de notas");
        int opcion = scanner.nextInt();

        if (opcion == 1) {
            visualizarHistorialNotas();
        } else {
            System.out.println("Opción no válida");
        }
    }

    public static void visualizarHistorialNotas() {
        System.out.println("Ingrese el código del estudiante:");
        int codigoAlumno = scanner.nextInt();
        Nota n1 = new Nota();
        List<Nota> notas =  notaService.obtenerNotasPorCodigoAlumno(codigoAlumno);

        if (Objects.isNull(notas)) {
            System.out.println("Clase no encontrada.");
        } else {
            //mostramos las notas
            notas.stream().forEach(nota -> {
                System.out.println(String.format("ID [%s] Curso [%s] Nota [%s]", nota.getId(), nota.getCurso().getNombre(), nota.getCalificacion()));
            });
        }
    }
}
