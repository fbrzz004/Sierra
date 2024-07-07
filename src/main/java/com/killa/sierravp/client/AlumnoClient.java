package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.domain.Ranking;
import com.killa.sierravp.repository.Universidad;
import com.killa.sierravp.service.CursoService;
import com.killa.sierravp.service.UsuarioService;
import com.killa.sierravp.util.CodigoGeneratorUsuario;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AlumnoClient {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NOMBRE_FACULTAD = "Facultad de Derecho y Ciencia Política";

    public static void main(String[] args) {
        Universidad universidad = GenerarFacultades.GenerarFacultadesCompletas(NOMBRE_FACULTAD);

        // Autenticar al usuario
        Alumno alumnoAutenticado = Login.autenticar(universidad);

        // Continuar con las funcionalidades después de la autenticación
        mostrarMenu();
        int opcion = SCANNER.nextInt();
        SCANNER.nextLine();  // Limpiar el buffer

        CU03yCU05ConsultarRendimiento consultarrendimiento = new CU03yCU05ConsultarRendimiento(universidad);
        CU07RecomendacionCompañerosMejorado recomendacionCompañeros = new CU07RecomendacionCompañerosMejorado(universidad);

        switch (opcion) {
            case 1:
                CU04VerEstadisticasClase(universidad);
                break;
            case 2:
                consultarRendimiento(consultarrendimiento);
                break;
            case 3:
                recomendacionCompañeros.RecomendarCompañeros(alumnoAutenticado);
                break;
            case 4:
                CU08ModificarPerfilUsuario(universidad);
                break;
            case 5:
                CU06GenerarYObtenerRanking(universidad);
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    private static void mostrarMenu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Ver estadísticas de la clase");
        System.out.println("2. Consultar rendimiento del alumno");
        System.out.println("3. Buscar recomendaciones de compañeros");
        System.out.println("4. Modificar perfil del usuario");
        System.out.println("5. Consultar ranking");
    }

    public static void CU04VerEstadisticasClase(Universidad universidad) {
        System.out.println("Ingrese el ID de la clase:");
        int idClase = SCANNER.nextInt();
        SCANNER.nextLine();  // Limpiar el buffer

        CursoService cursoService = new CursoService(universidad);
        List<Nota> notas = cursoService.obtenerNotasPorClase(idClase);

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
        int limiteSuperio = CodigoGeneratorUsuario.generate();
        System.out.println("Ingrese el código del alumno (300 <= id < " + limiteSuperio + "):");
        int codigoAlumno = SCANNER.nextInt();
        SCANNER.nextLine();

        consultarrendimiento.consultarRendimiento(codigoAlumno);
    }

    public static void mostrarInfoAlumno(Clase clase) {
        StringBuilder sb = new StringBuilder();
        sb.append(clase.getCurso().getNombre()).append(" y su id de curso es ").append(clase.getCurso().getId());
        Set<Clase> clases = clase.getCurso().getClases();
        System.out.println(sb.toString());
        System.out.println("Todas las clases de ese curso son:");
        for (Clase clase1 : clases) {
            System.out.println("Id de la clase: " + clase1.getId());
        }
    }

    public static void CU08ModificarPerfilUsuario(Universidad universidad) {
        System.out.println("Ingrese el ID del usuario:");
        int idUsuario = SCANNER.nextInt();
        SCANNER.nextLine();
        Alumno alumno = null;
        long inicio = System.nanoTime();
        alumno = universidad.obtenerAlumnoPorId(idUsuario);
        long fin = System.nanoTime();

        long tiempoTranscurrido = fin - inicio;
        System.out.println("Solucion Optima");
        System.out.println("Tiempo transcurrido: " + tiempoTranscurrido / 1_000_000.0 + " milisegundos");

        if (alumno == null) {
            System.out.println("No se encontró el usuario con ID " + idUsuario);
            return;
        }

        System.out.println("Credenciales actuales: ");
        System.out.println(" correo: " + alumno.getCorreo() + " password: " + alumno.getContraseña());

        System.out.println("Ingrese la contraseña:");
        String contraseña = SCANNER.nextLine();
        System.out.println("Ingrese el correo:");
        String correo = SCANNER.nextLine();

        alumno.actualizarPerfil(contraseña, correo);

        System.out.println("Perfil actualizado exitosamente.");
        System.out.println(" correo: " + alumno.getCorreo() + " password: " + alumno.getContraseña());
    }

    public static void CU06GenerarYObtenerRanking(Universidad universidad) {
        UsuarioService usuarioService = new UsuarioService(universidad);
        List<Ranking> rankings = usuarioService.obtenerYOrdenarRankings();
        System.out.println("Ranking generado y guardado exitosamente.");
        usuarioService.imprimirRankings(rankings);
    }
}
