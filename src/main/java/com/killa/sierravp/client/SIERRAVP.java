package com.killa.sierravp.client;

import com.killa.sierravp.service.UsuarioService;
import java.util.Scanner;

public class SIERRAVP {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioService usuarioService = new UsuarioService();

        System.out.println("Bienvenido al Sistema Integral de Evaluación del Rendimiento Académico y Formación de Vínculos Profesionales (SIERRAVP)");

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Consultar Clase por ID");
            System.out.println("2. Modificar Perfil de Usuario");
            System.out.println("3. Salir");
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
        scanner.nextLine();  // limpiar buffer
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
        
        boolean resultado = usuarioService.modificarPerfil(dni, n1,n2, a1, a2, contraseña, correo);
        if (resultado) {
            System.out.println("Perfil actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar el perfil. Usuario no encontrado.");
        }
    }
}
