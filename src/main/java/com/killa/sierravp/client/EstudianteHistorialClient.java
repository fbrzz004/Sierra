package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Curso;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.repository.Universidad;
import com.killa.sierravp.repository.ClaseRepository;
import com.killa.sierravp.repository.NotaRepository;
import com.killa.sierravp.service.ClaseService;
import com.killa.sierravp.service.CursoService;
import com.killa.sierravp.service.NotaService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class EstudianteHistorialClient {

    private static NotaService notaService;
    private static ClaseService claseService;
    private static CursoService cursoService;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Inicializar Universidad y servicios
        String nombreFacultad = "Facultad de Ciencias Físicas";
        Universidad universidad = GenerarFacultades.GenerarFacultadesCompletas(nombreFacultad);
        NotaRepository notaRepository = new NotaRepository();
        ClaseRepository claseRepository = new ClaseRepository();
        notaService = new NotaService(universidad);
        claseService = new ClaseService(claseRepository, notaRepository);
        cursoService = new CursoService(universidad);

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

        // Obtener las clases que tiene el estudiante
        List<Clase> clases = claseService.getClasesByAlumnoId(codigoAlumno);
        if (clases.isEmpty()) {
            System.out.println("El alumno no tiene clases disponibles.");
            return;
        }

        System.out.println("Ingrese el código de la clase:");
        clases.forEach(clase -> System.out.println(String.format("Clase con id [%s]", clase.getId())));
        int codigoClase = scanner.nextInt();

        // Obtener los cursos asociados a la clase seleccionada
        List<Curso> cursos = cursoService.getCursosByClaseId(codigoClase);
        if (cursos.isEmpty()) {
            System.out.println("El alumno no tiene cursos disponibles para la clase seleccionada.");
            return;
        }

        System.out.println("Ingrese el código del curso:");
        cursos.forEach(curso -> System.out.println(String.format("Curso con id [%s]", curso.getId())));
        int codigoCurso = scanner.nextInt();

        // Obtener las notas del estudiante para el curso seleccionado
        List<Nota> notas = notaService.obtenerNotasPorCodigoAlumno(codigoAlumno, codigoCurso);

        if (Objects.isNull(notas)) {
            System.out.println("Notas no encontradas.");
        } else {
            notas.forEach(nota -> System.out.println(String.format("ID [%s] Curso [%s] Nota [%s]", nota.getId(), nota.getCurso().getNombre(), nota.getCalificacion())));
        }
    }
}
