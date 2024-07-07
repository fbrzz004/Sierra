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
                    AlumnoClient.CU08ModificarPerfilUsuario(universidad);
                    break;
                case 3:
                    AlumnoClient.CU04VerEstadisticasClase(universidad);
                    break;
                case 4:
                    AlumnoClient.CU06GenerarYObtenerRanking(universidad);
                    break;
                case 5:
                    System.out.println("Saliendo del sistema.");
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
