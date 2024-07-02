package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Curso;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.service.ClaseService;
import com.killa.sierravp.service.CursoService;
import com.killa.sierravp.service.NotaService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class EstudianteHistorialClient {

    private static NotaService notaService = new NotaService();
    private static ClaseService claseService = new ClaseService();
    private static CursoService cursoService = new CursoService();
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
    //el metodo debe permitir que el estudiante busque sus notas de un curso en particular
    //debes añadir el codigo que permita obtener una lista de los cursos que llevo y que luego el alumno 
    public static void visualizarHistorialNotas() {
        System.out.println("Ingrese el código del estudiante:");
        int codigoAlumno = scanner.nextInt();
        //saber que curso tiene un estudiante
        List<Clase> clases = claseService.getClasesByAlumnoId(codigoAlumno);
        if (clases.isEmpty()) {
            System.out.println("El alumno no tiene clases disponibles.");
            return;
        }
        
        System.out.println("Ingrese el código de la clase:");
        clases.stream().forEach(clase -> {
                System.out.println(String.format("Clase con id [%s]", clase.getId()));
            });
        int codigoClase = scanner.nextInt();

        //a partir del codigo de estudiante, listar los cursos
        List<Curso> cursos =cursoService.getCursosByClaseId(codigoClase); 
        if (cursos.isEmpty()) {
            System.out.println("El alumno no tiene cursos disponibles para la clase seleccionada.");
            return;
        }
        
        System.out.println("Ingrese el código del curso:");
        cursos.stream().forEach(curso -> {
                System.out.println(String.format("Curso con id [%s]", curso.getId()));
            });
        int codigoCurso = scanner.nextInt();
        
        Nota n1 = new Nota();
        //aqui tienes que corregir el metodo de abajo para que se adapte a la logica de busqueda de curso
        //segun el comentario del encabezado del metodo visualizarHistorialNotas() , por cierto ya cambie el metodo que permite recuperar las notas segun el codigo y el id de curso 
        List<Nota> notas =  notaService.obtenerNotasPorCodigoAlumno(codigoAlumno, codigoCurso);

        if (Objects.isNull(notas)) {
            System.out.println("Notas no encontradas.");
        } else {
            //mostramos las notas
            notas.stream().forEach(nota -> {
                System.out.println(String.format("ID [%s] Curso [%s] Nota [%s]", nota.getId(), nota.getCurso().getNombre(), nota.getCalificacion()));
            });
        }
    }
}
