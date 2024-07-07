package com.killa.sierravp.client;

import com.killa.sierravp.domain.Ranking;
import com.killa.sierravp.service.UsuarioService;
import com.killa.sierravp.service.ClaseService;
import com.killa.sierravp.service.RankingService;
import com.killa.sierravp.repository.ClaseRepository;
import com.killa.sierravp.repository.NotaRepository;
import com.killa.sierravp.repository.Universidad;
import java.util.List;
import java.util.Scanner;

public class SIERRAVP {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Universidad universidad = new Universidad();
        UsuarioService usuarioService = new UsuarioService(universidad);
        ClaseRepository claseRepository = new ClaseRepository();
        NotaRepository notaRepository = new NotaRepository();
        ClaseService claseService = new ClaseService(claseRepository, notaRepository);
        RankingService rankingService = new RankingService(universidad);

        System.out.println("Bienvenido al Sistema Integral de Evaluación del Rendimiento Académico y Formación de Vínculos Profesionales (SIERRAVP)");

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Consultar Clase por ID");
            System.out.println("2. Modificar Perfil de Usuario");
            System.out.println("3. Ver Estadísticas de Clase");
            System.out.println("4. Generar Ranking por Facultad y Escuela Profesional");
            System.out.println("5. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    ClaseClient.main(args);
                    break;
                case 2:
                    modificarPerfil(scanner, usuarioService);
                    break;
                case 3:
                    verEstadisticasClase(scanner, claseService);
                    break;
                case 4:
                    generarRanking(usuarioService);
                    break;
                case 5:
                    System.out.println("Saliendo del sistema.");
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void modificarPerfil(Scanner scanner, UsuarioService usuarioService) {
        System.out.println("Ingrese su DNI:");
        int dni = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese su primer nombre:");
        String n1 = scanner.nextLine();
        System.out.println("Ingrese su segundo nombre:");
        String a1 = scanner.nextLine();
        System.out.println("Ingrese su apellido paterno:");
        String n2 = scanner.nextLine();
        System.out.println("Ingrese su apellido materno:");
        String a2 = scanner.nextLine();
        System.out.println("Ingrese su nueva contraseña:");
        String contraseña = scanner.nextLine();
        System.out.println("Ingrese su nuevo correo:");
        String correo = scanner.nextLine();

        boolean resultado = usuarioService.modificarPerfil(dni, n1, n2, a1, a2, contraseña, correo);
        if (resultado) {
            System.out.println("Perfil actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar el perfil. Usuario no encontrado.");
        }
    }

    private static void verEstadisticasClase(Scanner scanner, ClaseService claseService) {
        System.out.println("Ingrese el ID de la clase para consultar estadísticas:");
        int claseId = scanner.nextInt();
        String estadisticas = claseService.obtenerEstadisticasClase(claseId);
        System.out.println(estadisticas);
    }

    private static void generarRanking(UsuarioService usuarioService) {
        List<Ranking> rankings = usuarioService.obtenerYOrdenarRankings();
        System.out.println("Ranking generado y guardado exitosamente.");
        usuarioService.imprimirRankings(rankings);
    }
}
