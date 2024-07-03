package com.killa.sierravp.client;

import com.killa.sierravp.service.AlumnoService;
import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.repository.Universidad;
import com.killa.sierravp.service.CursoService;
import java.util.List;
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
        System.out.println("3. Buscar recomendaciones de compañeros");
        int opcion = scanner.nextInt();
        Alumno alumnoBusca = new Alumno();
        String nombreFacultad = "Facultad de Ciencias Físicas";
        Universidad universidad = GenerarFacultades.GenerarFacultadesCompletas(nombreFacultad);
        Universidad.FacultadData facultadData = universidad.obtenerFacultad(nombreFacultad);
        Universidad.EscuelaData escuelaFisica = facultadData.obtenerEscuela(103);
        if (escuelaFisica != null) {
            for (Alumno alumno : escuelaFisica.getAlumnos()) {
                if (alumno.getCiclo() == 5) {
                    alumnoBusca = alumno;
                    break;
                }
            }
        } else {
            System.out.println("No se encontró la escuela de Física.");
        }

        switch (opcion) {
            case 1:
                //verEstadisticasClase();
                break;
            case 2:
                consultarRendimiento();
                break;
            case 3:
                CU07RecomendacionCompañeros.RecomendarCompañeros(alumnoBusca, universidad);
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    public static void verEstadisticasClase() {
        System.out.println("Ingrese el ID de la clase:");
        int idClase = scanner.nextInt();

        //List<Nota> notas = cursoService.obtenerNotasPorClase(idClase);
        List<Nota> notas = null;//que ahora recupere de memoria
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
    public static void consultarRendimiento() {
        System.out.println("Ingrese su Codigo de estudiante: ");
        int cod = scanner.nextInt();

        //Alumno alumno = alumnoService.findByCodigo(cod);
        Alumno alumno = null;
        if (alumno != null) {
            System.out.println(alumno.getPrimerNombre() + " " + alumno.getPrimerApellido());
            /*
            System.out.println("Posición en el ranking: " + alumno.getPosicionRanking());
            System.out.println("CRA Ponderado Actual: " + alumno.getCraPonderadoActual());
            System.out.println("Histórico de CRA: " + alumno.getCraHistorico());
             */
            Set<Clase> clases = alumno.getClases();
            System.out.print("El alumno lleva ");
            System.out.println("");
            for (Clase clase : clases) {
                mostrarInfoAlumno(clase);
            }
        } else {
            System.out.println("No se pudo recuperar la información de rendimiento.");
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
