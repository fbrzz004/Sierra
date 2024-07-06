package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Profesor;
import com.killa.sierravp.repository.Universidad;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author jaraj
 */
public class Login {
    private static String nombreFacultad = "Facultad de Derecho y Ciencia Política";
    private static JTextArea credencialesArea;
    private static JTextField correoField;
    private static JPasswordField contraseñaField;
    private static JFrame frame; // Mantenemos una referencia al frame para cerrarlo después

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    public static void createAndShowGUI() {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); // Tamaño del frame
        frame.setLayout(new BorderLayout());

        // Panel para mostrar las credenciales
        JPanel panelCredenciales = new JPanel(new BorderLayout());
        credencialesArea = new JTextArea();
        credencialesArea.setEditable(false); // Campo no editable antes del login
        credencialesArea.setMargin(new Insets(10, 10, 10, 10)); // Márgenes internos
        credencialesArea.setFont(new Font("Arial", Font.PLAIN, 12)); // Fuente y tamaño de texto
        JScrollPane scrollPane = new JScrollPane(credencialesArea);
        panelCredenciales.add(new JLabel("Credenciales de ejemplo para login:"), BorderLayout.NORTH);
        panelCredenciales.add(scrollPane, BorderLayout.CENTER);

        // Panel para el formulario de login
        JPanel panelLogin = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Márgenes entre componentes

        JLabel correoLabel = new JLabel("Correo:");
        correoField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelLogin.add(correoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelLogin.add(correoField, gbc);

        JLabel contraseñaLabel = new JLabel("Contraseña:");
        contraseñaField = new JPasswordField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelLogin.add(contraseñaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panelLogin.add(contraseñaField, gbc);

        JButton alumnoButton = new JButton("Login como Alumno");
        alumnoButton.addActionListener(e -> loginComoAlumno());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelLogin.add(alumnoButton, gbc);

        JButton profesorButton = new JButton("Login como Profesor");
        profesorButton.addActionListener(e -> loginComoProfesor());
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelLogin.add(profesorButton, gbc);

        JButton iniciarSesionButton = new JButton("Iniciar Sesión");
        iniciarSesionButton.addActionListener(e -> iniciarSesion());
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelLogin.add(iniciarSesionButton, gbc);

        // Añadir paneles al frame
        frame.add(panelCredenciales, BorderLayout.NORTH);
        frame.add(panelLogin, BorderLayout.CENTER);

        // Centrar el frame en la pantalla
        frame.setLocationRelativeTo(null);

        // Mostrar frame
        frame.setVisible(true);
    }

    public static void loginComoAlumno() {
        Universidad universidad = GenerarFacultades.GenerarFacultadesCompletas(nombreFacultad);
        Alumno alumno = universidad.obtenerAlumnoPorId(500); // ID de alumno de ejemplo
        String correoAlumno = alumno.getCorreo();
        String contraseñaAlumno = alumno.getContraseña();
        String credencialesTexto = "Alumno - Correo: " + correoAlumno + ", Contraseña: " + contraseñaAlumno;
        actualizarCredenciales(credencialesTexto);
    }

    public static void loginComoProfesor() {
        Universidad universidad = GenerarFacultades.GenerarFacultadesCompletas(nombreFacultad);
        Profesor profesor = universidad.obtenerProfesorPorId(100); // ID de profesor de ejemplo
        String correoProfesor = profesor.getCorreo();
        String contraseñaProfesor = profesor.getContraseña();
        String credencialesTexto = "Profesor - Correo: " + correoProfesor + ", Contraseña: " + contraseñaProfesor;
        actualizarCredenciales(credencialesTexto);
    }

    public static void iniciarSesion() {
        String correoIngresado = correoField.getText();
        String contraseñaIngresada = new String(contraseñaField.getPassword());

        // Lógica para verificar las credenciales y realizar el inicio de sesión
        // Por ahora, simplemente mostramos un mensaje de éxito
        JOptionPane.showMessageDialog(null,
                "Inicio de sesión exitoso como usuario.",
                "Inicio de Sesión",
                JOptionPane.INFORMATION_MESSAGE);

        // Cerrar el frame actual de Login
        frame.dispose();

        // Abrir AlumnoClient
        AlumnoClient.createAndShowGUI();
    }

    public static void actualizarCredenciales(String credenciales) {
        credencialesArea.setText(credenciales);
    }
}
