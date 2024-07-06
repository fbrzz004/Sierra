package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.repository.Universidad;
import com.killa.sierravp.service.CursoService;
import com.killa.sierravp.util.CodigoGeneratorUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

public class AlumnoClient {

    private static Universidad universidad;
    private static CU03yCU05ConsultarRendimiento consultarrendimiento;
    private static CU07RecomendacionCompañerosMejorado recomendacionCompañeros;

    public static void main(String[] args) {
        universidad = GenerarFacultades.GenerarFacultadesCompletas("Facultad de Derecho y Ciencia Política");
        consultarrendimiento = new CU03yCU05ConsultarRendimiento(universidad);
        recomendacionCompañeros = new CU07RecomendacionCompañerosMejorado(universidad);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    static void createAndShowGUI() {
        JFrame frame = new JFrame("Alumno Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel label = new JLabel("Seleccione una opción:");
        label.setBounds(10, 20, 200, 25);
        panel.add(label);

        JButton estadisticasButton = new JButton("Ver estadísticas de la clase");
        estadisticasButton.setBounds(10, 50, 200, 25);
        panel.add(estadisticasButton);
        estadisticasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verEstadisticasClase();
            }
        });

        JButton rendimientoButton = new JButton("Consultar rendimiento del alumno");
        rendimientoButton.setBounds(10, 80, 200, 25);
        panel.add(rendimientoButton);
        rendimientoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarRendimiento();
            }
        });

        JButton recomendacionesButton = new JButton("Buscar recomendaciones de compañeros");
        recomendacionesButton.setBounds(10, 110, 250, 25);
        panel.add(recomendacionesButton);
        recomendacionesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarRecomendaciones();
            }
        });

        JButton modificarPerfilButton = new JButton("Modificar perfil del usuario");
        modificarPerfilButton.setBounds(10, 140, 200, 25);
        panel.add(modificarPerfilButton);
        modificarPerfilButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarPerfilUsuario();
            }
        });
    }

    private static void verEstadisticasClase() {
        String idClaseStr = JOptionPane.showInputDialog("Ingrese el ID de la clase:");
        int idClase = Integer.parseInt(idClaseStr);

        CursoService cursoService = new CursoService(universidad);
        List<Nota> notas = null; // Aquí deberías obtener las notas de la clase desde la memoria

        if (notas == null || notas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron notas para la clase con ID " + idClase);
            return;
        }

        double media = cursoService.calcularMedia(notas);
        int notaMinima = cursoService.obtenerNotaMinima(notas);
        int notaMaxima = cursoService.obtenerNotaMaxima(notas);

        String mensaje = String.format("Estadísticas de la clase:\nMedia de notas: %.2f\nNota mínima: %d\nNota máxima: %d", media, notaMinima, notaMaxima);
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private static void consultarRendimiento() {
        int limiteSuperio = CodigoGeneratorUsuario.generate();
        String codigoAlumnoStr = JOptionPane.showInputDialog("Ingrese el código del alumno (300 <= id < " + limiteSuperio + ")");
        int codigoAlumno = Integer.parseInt(codigoAlumnoStr);

        consultarrendimiento.consultarRendimiento(codigoAlumno);
    }

    private static void buscarRecomendaciones() {
        Alumno alumno = universidad.obtenerAlumnoPorId(500); // Aquí debe ir el id del alumno que se logee
        recomendacionCompañeros.RecomendarCompañeros(alumno);
    }

    private static void modificarPerfilUsuario() {
        String idUsuarioStr = JOptionPane.showInputDialog("Ingrese el ID del usuario:");
        int idUsuario = Integer.parseInt(idUsuarioStr);

        long inicio = System.nanoTime();
        Alumno alumno = universidad.obtenerAlumnoPorId(idUsuario);
        long fin = System.nanoTime();
        long tiempoTranscurrido = fin - inicio;

        if (alumno == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el usuario con ID " + idUsuario);
            return;
        }

        String mensaje = String.format("Crendiales actuales:\ncorreo: %s\npassword: %s\nTiempo transcurrido: %.2f milisegundos", alumno.getCorreo(), alumno.getContraseña(), tiempoTranscurrido / 1_000_000.0);
        JOptionPane.showMessageDialog(null, mensaje);

        String contraseña = JOptionPane.showInputDialog("Ingrese la contraseña:");
        String correo = JOptionPane.showInputDialog("Ingrese el correo:");

        alumno.actualizarPerfil(contraseña, correo);

        String mensajeActualizado = String.format("Perfil actualizado exitosamente.\ncorreo: %s\npassword: %s", alumno.getCorreo(), alumno.getContraseña());
        JOptionPane.showMessageDialog(null, mensajeActualizado);
    }
}


/*package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.domain.Profesor;
import com.killa.sierravp.repository.Universidad;
import com.killa.sierravp.service.CursoService;
import com.killa.sierravp.util.CodigoGeneratorUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AlumnoClient {

    private static Scanner scanner = new Scanner(System.in);
    public static void menu(Object usuario) {
        if (usuario instanceof Alumno) {
            Alumno alumno = (Alumno) usuario;
            System.out.println("Menú del alumno:");
            System.out.println("1. Ver estadísticas de la clase");
            System.out.println("2. Consultar rendimiento del alumno");
            System.out.println("3. Buscar recomendaciones de compañeros");
            System.out.println("4. Modificar perfil del usuario");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer
            String nombreFacultad = null;

            Universidad universidad = GenerarFacultades.GenerarFacultadesCompletas(nombreFacultad);
            CU03yCU05ConsultarRendimiento consultarrendimiento = new CU03yCU05ConsultarRendimiento(universidad);
            CU07RecomendacionCompañerosMejorado recomendacionCompañeros = new CU07RecomendacionCompañerosMejorado(universidad);

            switch (opcion) {
                case 1:
                    verEstadisticasClase(universidad);
                    break;
                case 2:
                    consultarRendimiento(consultarrendimiento);
                    break;
                case 3:
                    recomendacionCompañeros.RecomendarCompañeros(alumno);
                    break;
                case 4:
                    modificarPerfilUsuario(universidad, alumno);
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } else if (usuario instanceof Profesor) {
            Profesor profesor = (Profesor) usuario;
            System.out.println("Menú del profesor:");
            System.out.println("1. Ver estadísticas de la clase");
            System.out.println("2. Modificar perfil del usuario");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    verEstadisticasClaseProfesor(profesor);
                    break;
                case 2:
                    modificarPerfilUsuarioProfesor(profesor);
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    public static void verEstadisticasClase(Universidad universidad) {
        System.out.println("Ingrese el ID de la clase:");
        int idClase = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        CursoService cursoService = new CursoService(universidad);
        List<Nota> notas = null;  // Aquí deberías obtener las notas de la clase desde la memoria

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
        int limiteSuperio=CodigoGeneratorUsuario.generate();
        System.out.println("Ingrese el código del alumno (300 "+" <= "+" id "+ " < "+limiteSuperio);
        int codigoAlumno = scanner.nextInt();
        scanner.nextLine(); 

        consultarrendimiento.consultarRendimiento(codigoAlumno);
    }

    public static void recomendacionCompañeros(CU07RecomendacionCompañerosMejorado recomendacionCompañeros, Alumno alumno) {
        recomendacionCompañeros.RecomendarCompañeros(alumno);
    }

    public static void modificarPerfilUsuario(Universidad universidad, Alumno alumno) {
        System.out.println("Ingrese el ID del usuario:");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();  

        long inicio = 0;
        long fin = 0;
        long tiempoTranscurrido = 0;
        inicio = System.nanoTime();
        alumno = universidad.obtenerAlumnoPorId(idUsuario);
        fin = System.nanoTime();

        tiempoTranscurrido = fin - inicio;
        System.out.println("Solucion Optima");
        System.out.println("Tiempo transcurrido: " + tiempoTranscurrido / 1_000_000.0 + " milisegundos");

        if (alumno == null) {
            System.out.println("No se encontró el usuario con ID " + idUsuario);
            return;
        }

        System.out.println("Crendiales actuales: ");
        System.out.println(" correo: " + alumno.getCorreo() + " password: "+alumno.getContraseña());

        System.out.println("Ingrese la contraseña:");
        String contraseña = scanner.nextLine();
        System.out.println("Ingrese el correo:");
        String correo = scanner.nextLine();

        alumno.actualizarPerfil(contraseña, correo);

        System.out.println("Perfil actualizado exitosamente.");
        System.out.println(" correo: " + alumno.getCorreo() + " password: "+alumno.getContraseña());
    }

    public static void verEstadisticasClaseProfesor(Profesor profesor) {
        // Implementación de la funcionalidad para el profesor
        System.out.println("Funcionalidad no implementada para el profesor en este ejemplo.");
    }

    public static void modificarPerfilUsuarioProfesor(Profesor profesor) {
        // Implementación de la funcionalidad para el profesor
        System.out.println("Funcionalidad no implementada para el profesor en este ejemplo.");
    }
}

*/
/*package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.repository.Universidad;
import com.killa.sierravp.service.CursoService;
import com.killa.sierravp.util.CodigoGeneratorUsuario;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AlumnoClient {

    private static Scanner scanner = new Scanner(System.in);

    private static String nombreFacultad = "Facultad de Derecho y Ciencia Política"; // Nombre de la facultad Facultad de Ciencias Fisicas

    public static void main(String[] args) {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Ver estadísticas de la clase");
        System.out.println("2. Consultar rendimiento del alumno");
        System.out.println("3. Buscar recomendaciones de compañeros");
        System.out.println("4. Modificar perfil del usuario");  // Nueva opción
        int opcion = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        Universidad universidad = GenerarFacultades.GenerarFacultadesCompletas(nombreFacultad);
        CU03yCU05ConsultarRendimiento consultarrendimiento = new CU03yCU05ConsultarRendimiento(universidad);
        CU07RecomendacionCompañerosMejorado recomendacionCompañeros = new CU07RecomendacionCompañerosMejorado(universidad);

        switch (opcion) {
            case 1:
                verEstadisticasClase(universidad);
                break;
            case 2:
                consultarRendimiento(consultarrendimiento);
                break;
            case 3:
                Alumno alumno = universidad.obtenerAlumnoPorId(500); // Aquí debe ir el id del alumno que se logee
                // o mejor lo pasas como parámetro, lo envío así para poder probar nomás
                recomendacionCompañeros.RecomendarCompañeros(alumno);
                break;
            case 4:
                modificarPerfilUsuario(universidad);
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    public static void verEstadisticasClase(Universidad universidad) {
        System.out.println("Ingrese el ID de la clase:");
        int idClase = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        CursoService cursoService = new CursoService(universidad);
        List<Nota> notas = null;  // Aquí deberías obtener las notas de la clase desde la memoria

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
        int limiteSuperio=CodigoGeneratorUsuario.generate();
        System.out.println("Ingrese el código del alumno (300 "+" <= "+" id "+ " < "+limiteSuperio);
        int codigoAlumno = scanner.nextInt();
        scanner.nextLine(); 

        consultarrendimiento.consultarRendimiento(codigoAlumno);
        
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
    
    public static void modificarPerfilUsuario(Universidad universidad) {
        System.out.println("Ingrese el ID del usuario:");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();  
        Alumno alumno = null;
        long inicio = 0;
        long fin = 0;
        long tiempoTranscurrido = 0;
        inicio = System.nanoTime();
        alumno = universidad.obtenerAlumnoPorId(idUsuario);
        fin = System.nanoTime();
        
        tiempoTranscurrido = fin - inicio;
        System.out.println("Solucion Optima");
        System.out.println("Tiempo transcurrido: " + tiempoTranscurrido / 1_000_000.0 + " milisegundos");
        
        if (alumno == null) {
            System.out.println("No se encontró el usuario con ID " + idUsuario);
            return;
        }

        System.out.println("Crendiales actuales: ");
        System.out.println(" correo: " + alumno.getCorreo() + " password: "+alumno.getContraseña());
        
        System.out.println("Ingrese la contraseña:");
        String contraseña = scanner.nextLine();
        System.out.println("Ingrese el correo:");
        String correo = scanner.nextLine();

        alumno.actualizarPerfil(contraseña, correo);

        System.out.println("Perfil actualizado exitosamente.");
        System.out.println(" correo: " + alumno.getCorreo() + " password: "+alumno.getContraseña());
    }
}
*/