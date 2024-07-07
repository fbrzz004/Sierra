package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.repository.Universidad;
import java.util.Scanner;

public class Login {
    private static final String NOMBRE_FACULTAD = "Facultad de Derecho y Ciencia Política";

    public static Alumno autenticar(Universidad universidad) {
        // Obtener dos alumnos de ejemplo
        Alumno alumno1 = universidad.obtenerAlumnoPorId(500); // ID de alumno de ejemplo 1
        Alumno alumno2 = universidad.obtenerAlumnoPorId(501); // ID de alumno de ejemplo 2

        // Mostrar las credenciales de ejemplo para login
        String credencialesAlumno1 = "Usuario 1 - Correo: " + alumno1.getCorreo() + ", Contraseña: " + alumno1.getContraseña();
        String credencialesAlumno2 = "Usuario 2 - Correo: " + alumno2.getCorreo() + ", Contraseña: " + alumno2.getContraseña();

        System.out.println("Credenciales de ejemplo para login:");
        System.out.println(credencialesAlumno1);
        System.out.println(credencialesAlumno2);

        // Simular el inicio de sesión
        Scanner scanner = new Scanner(System.in);
        Alumno alumnoAutenticado = null;

        while (alumnoAutenticado == null) {
            System.out.print("Ingrese su correo: ");
            String correoIngresado = scanner.nextLine();

            System.out.print("Ingrese su contraseña: ");
            String contraseñaIngresada = scanner.nextLine();

            // Verificación de las credenciales
            if (correoIngresado.equals(alumno1.getCorreo()) && contraseñaIngresada.equals(alumno1.getContraseña())) {
                System.out.println("Inicio de sesión exitoso.");
                alumnoAutenticado = alumno1;
            } else if (correoIngresado.equals(alumno2.getCorreo()) && contraseñaIngresada.equals(alumno2.getContraseña())) {
                System.out.println("Inicio de sesión exitoso.");
                alumnoAutenticado = alumno2;
            } else {
                System.out.println("Inicio de sesión fallido. Credenciales incorrectas. Por favor, intente nuevamente.");
            }
        }

        return alumnoAutenticado;
    }
}
