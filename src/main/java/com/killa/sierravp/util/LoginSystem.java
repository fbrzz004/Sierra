/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.util;

/**
 *
 * @author HITV
 */
import com.killa.sierravp.domain.Administrativo;
import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.BigFiveScores;
import com.killa.sierravp.domain.Profesor;
import com.killa.sierravp.domain.Usuario;
import com.killa.sierravp.repository.BigFiveScoresRepository;
import com.killa.sierravp.repository.Universidad;
import com.killa.sierravp.repository.Universidad.EscuelaData;
import com.killa.sierravp.repository.Universidad.FacultadData;
import java.util.Scanner;

public class LoginSystem {

    private Universidad universidad;

    public LoginSystem(Universidad universidad) {
        this.universidad = universidad;
    }

    public Alumno autenticarAlumno(String correo, String contraseña) {
        for (FacultadData facultad : universidad.getFacultades().values()) {
            for (EscuelaData escuela : facultad.getEscuelas().values()) {
                for (Alumno a : escuela.getAlumnos()) {
                    if (a.getCorreo().equals(correo) && a.getContraseña().equals(contraseña)) {
                        return a;
                    }
                }
            }
        }
        return null;
    }
    
    public Profesor autenticarProfesor(String correo, String contraseña) {
        for (FacultadData facultad : universidad.getFacultades().values()) {
            for (EscuelaData escuela : facultad.getEscuelas().values()) {
                for (Profesor p : escuela.getProfesores()) {
                    if (p.getCorreo().equals(correo) && p.getContraseña().equals(contraseña)) {
                        return p;
                    }
                }
            }
        }
        return null;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Correo: ");
        String correo = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        System.out.print("Tipo de usuario (Alumno/Profesor): ");
        String tipo = scanner.nextLine();

        Usuario usuario=null;
        
        switch (tipo) {
            case "Alumno":
                usuario = autenticarAlumno(correo, contraseña);
                break;
            case "Profesor":
                usuario = autenticarProfesor(correo, contraseña);
                break;
            default:
                throw new AssertionError();
        }
        
        if (usuario != null) {
            System.out.println("Bienvenido " + usuario.getPrimerNombre());
            mostrarMenu(usuario);
        } else {
            System.out.println("Correo o contraseña incorrectos.");
        }
    }

    private void mostrarMenu(Usuario usuario) {
        if (usuario instanceof Alumno) {
            mostrarMenuAlumno((Alumno) usuario);
        } else if (usuario instanceof Profesor) {
            mostrarMenuProfesor((Profesor) usuario);
        } else if (usuario instanceof Administrativo) {
            mostrarMenuAdministrador((Administrativo) usuario);
        }
    }

    private void mostrarMenuAlumno(Alumno alumno) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú Alumno");
            System.out.println("1. Ver notas");
            System.out.println("2. Ver horarios");
            System.out.println("3. Ver BF5 Scores");
            System.out.println("4. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Implementar lógica para ver notas
                    break;
                case 2:
                    // Implementar lógica para ver horarios
                    break;
                case 3:
                    // Implementar lógica para ver BF5 Scores
                    BigFiveScoresRepository repo = new BigFiveScoresRepository();
                    BigFiveScores bfs = repo.obtenerByCodigo(alumno.getCodigo());
                    if (bfs != null) {
                        System.out.println("BF5 Scores: " + bfs.toString());
                    } else {
                        System.out.println("No se encontraron BF5 Scores.");
                    }
                    break;
                case 4:
                    System.out.println("Sesión cerrada.");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 4);
    }

    private void mostrarMenuProfesor(Profesor profesor) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú Profesor");
            System.out.println("1. Ver lista de alumnos");
            System.out.println("2. Ver horarios");
            System.out.println("3. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Implementar lógica para ver lista de alumnos
                    break;
                case 2:
                    // Implementar lógica para ver horarios
                    break;
                case 3:
                    System.out.println("Sesión cerrada.");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 3);
    }

    private void mostrarMenuAdministrador(Administrativo administrador) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú Administrador");
            System.out.println("1. Ver todas las facultades");
            System.out.println("2. Agregar usuario");
            System.out.println("3. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Implementar lógica para ver todas las facultades
                    break;
                case 2:
                    // Implementar lógica para agregar usuario
                    break;
                case 3:
                    System.out.println("Sesión cerrada.");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 3);
    }
}
