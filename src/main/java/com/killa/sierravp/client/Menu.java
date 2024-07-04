/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.domain.Profesor;
import com.killa.sierravp.repository.Universidad;
import java.util.LinkedList;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private Universidad universidad;
    private Login login;

    public Menu(Universidad universidad, Login login) {
        this.universidad = universidad;
        this.login = login;
    }

    public void iniciarMenu() {
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su correo electrónico: ");
        String correo = scanner.nextLine();

        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();

        int codigoUsuario = login.obtenerCodigoUsuarioPorCorreo(correo, contrasena);
        if (codigoUsuario == -1) {
            System.out.println("Correo electrónico o contraseña incorrectos.");
            return;
        }

        Alumno alumno = universidad.obtenerAlumnoPorCodigo(codigoUsuario);
        if (alumno == null) {
            System.out.println("Alumno no encontrado.");
            return;
        }

        mostrarMenuAlumno(alumno, scanner);
    }

    private void mostrarMenuAlumno(Alumno alumno, Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n--- Menú Alumno ---");
            System.out.println("1. Ver datos personales");
            System.out.println("2. Ver notas");
            System.out.println("3. Ver rendimiento (CRA)");
            System.out.println("4. Buscar recomendaciones");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea pendiente

            switch (opcion) {
                case 1:
                    verDatosPersonales(alumno);
                    break;
                case 2:
                    verNotas(alumno);
                    break;
                case 3:
                    verRendimientoCRA(alumno);
                    break;
                case 4:
                    buscarRecomendaciones(alumno, scanner);
                    break;
                case 5:
                    System.out.println("Saliendo del menú alumno...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 5);
    }

    private void verDatosPersonales(Alumno alumno) {
        System.out.println("\n--- Datos Personales ---");
        System.out.println("Nombre: " + alumno.getNombre());
        System.out.println("Código: " + alumno.getCodigo());
        System.out.println("Escuela: " + alumno.getEp());
        System.out.println("Facultad: " + alumno.getFacultad());
    }

    private void verNotas(Alumno alumno) {
        List<Nota> notas = universidad.obtenerNotasDeAlumno(alumno);
        if (notas.isEmpty()) {
            System.out.println("No hay notas disponibles.");
        } else {
            System.out.println("\n--- Notas ---");
            for (Nota nota : notas) {
                System.out.println("Curso: " + nota.getCurso().getNombre() + ", Nota: " + nota.getValor());
            }
        }
    }

    private void verRendimientoCRA(Alumno alumno) {
        double cra = universidad.calcularCRAAlumno(alumno);
        System.out.println("\n--- Rendimiento (CRA) ---");
        System.out.println("CRA: " + cra);
    }

    private void buscarRecomendaciones(Alumno alumno, Scanner scanner) {
        List<String> intereses = universidad.obtenerInteresesAlumno(alumno);

        if (intereses.isEmpty()) {
            System.out.println("El alumno no tiene intereses registrados.");
            return;
        }

        System.out.println("\n--- Buscar Recomendaciones ---");
        System.out.println("Seleccione un interés o habilidad que desea maximizar entre sus recomendados:");
        for (int i = 0; i < intereses.size(); i++) {
            System.out.println((i + 1) + ". " + intereses.get(i));
        }
        System.out.print("Ingrese el número correspondiente: ");
        int seleccion1 = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea pendiente

        if (seleccion1 < 1 || seleccion1 > intereses.size()) {
            System.out.println("Selección inválida.");
            return;
        }

        String interes1 = intereses.get(seleccion1 - 1);

        System.out.println("\nSeleccione un interés o habilidad que no desea:");
        for (int i = 0; i < intereses.size(); i++) {
            if (i != seleccion1 - 1) {
                System.out.println((i + 1) + ". " + intereses.get(i));
            }
        }
        System.out.print("Ingrese el número correspondiente: ");
        int seleccion2 = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea pendiente

        if (seleccion2 < 1 || seleccion2 > intereses.size() || seleccion2 == seleccion1) {
            System.out.println("Selección inválida.");
            return;
        }

        String interes2 = intereses.get(seleccion2 - 1);

        List<Alumno> recomendaciones = universidad.buscarRecomendacionesAlumnos(alumno, interes1, interes2);

        if (recomendaciones.isEmpty()) {
            System.out.println("No se encontraron recomendaciones.");
        } else {
            System.out.println("\n--- Recomendaciones ---");
            for (Alumno recom : recomendaciones) {
                System.out.println("--------------------------------------------------");
                System.out.println("Nombre: " + recom.getNombre());
                System.out.println("Código: " + recom.getCodigo());
                System.out.println("Escuela: " + recom.getEp());
                System.out.println("Facultad: " + recom.getFacultad());
                System.out.println("--------------------------------------------------");
            }
        }
    }

    public static void main(String[] args) {
        String nombreFacultad = "Facultad de Psicología"; // Nombre de la facultad Facultad de Ciencias Fisicas
        Universidad universidad = GenerarFacultades.GenerarFacultadesCompletas(nombreFacultad); // Supongamos que ya tienes una instancia de Universidad
        Login login = new Login(universidad); // Instancia de Login, que permite autenticar usuarios

        Menu menu = new Menu(universidad, login); // Instancia de Menu, que utiliza Universidad y Login
        Alumno alumno = universidad.obtenerAlumnoPorId(500);
        Profesor profesor = universidad.obtenerProfesorPorId(150);
        System.out.println("Alumno: ");
        System.out.println(alumno.getCorreo() + "  "+ alumno.getContraseña());
        System.out.println(alumno.getNotas().size() + "             ddddddddddddddddddd");
        for (Nota n : alumno.getNotas()) {
            System.out.println(n.getTipo().toString() + " curso: "+ n.getCurso().getNombre() +" nota: "+ n.getCalificacion());
        }

        System.out.println("");
        System.out.println(alumno.getCorreo() + "  "+ alumno.getContraseña());
        System.out.println("Profesor: ");
        System.out.println(profesor.getCorreo() + "  "+ profesor.getContraseña());
        System.out.println(profesor.getCorreo() + "  "+ profesor.getContraseña());
        menu.iniciarMenu(); // Inicia el menú para el usuario
    }

    private String getFacultad() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
