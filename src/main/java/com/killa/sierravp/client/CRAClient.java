package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.CRA;
import com.killa.sierravp.repository.Universidad;
import com.killa.sierravp.service.CRAService;
import com.killa.sierravp.client.GenerarFacultades;

import java.util.Scanner;

public class CRAClient {
    private static Universidad universidad;
    private static CRAService craService;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String nombreFacultad = "Facultad de Derecho y Ciencia Política"; // Nombre de la facultad
        universidad = GenerarFacultades.GenerarFacultadesCompletas(nombreFacultad);
        craService = new CRAService(universidad);

        // Obtener el código del alumno
        System.out.println("Ingrese el código del alumno:");
        int codigoAlumno = scanner.nextInt();

        // Calcular el CRA del alumno
        craService.mostrarNotasAlumno(codigoAlumno);

        // Obtener el alumno y mostrar su CRA ponderado actual
        Alumno alumno = universidad.obtenerAlumnoPorId(codigoAlumno);
        if (alumno != null) {
            System.out.println("CRA Ponderado Actual: " + alumno.getCraPonderadoActual());
        } else {
            System.out.println("No se encontró un alumno con el código proporcionado.");
        }
    }
}

