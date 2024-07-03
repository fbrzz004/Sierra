/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.repository.Universidad;
import com.killa.sierravp.service.NetworkService;
import com.killa.sierravp.util.AtributoBf5;
import com.killa.sierravp.util.AtributosInteresesAcade;
import com.killa.sierravp.util.Caracteristica_y_Id;
import com.killa.sierravp.util.Caractistica;
import com.killa.sierravp.service.AlumnoService;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author HITV
 */
public class CU07RecomendacionCompañeros {

    //por el momento solo trabajo con una facultad
    public static void RecomendarCompañeros(Alumno alumno, Universidad universidad) {
        Scanner scanner = new Scanner(System.in);
        String nombreFacultad = alumno.getEp().getFacultad().getNombre();
        //String nombreFacultad = "Facultad de Ciencias Físicas";
        //Universidad.FacultadData facultadData = universidad.obtenerFacultad(nombreFacultad);

        System.out.println("Generar recomendación de compañeros");

        System.out.println("Características de personalidad:");
        AtributoBf5[] values = AtributoBf5.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(i + ". " + values[i]);
        }

        System.out.println("Intereses académicos:");
        AtributosInteresesAcade[] intereses = AtributosInteresesAcade.values();
        for (int i = 0; i < intereses.length; i++) {
            System.out.println(i + ". " + intereses[i]);
        }

        System.out.println("Ingrese la característica que desea maximizar entre sus recomendados");
        System.out.println("1 para características de personalidad, 2 para intereses académicos:");
        int opc = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Caracteristica_y_Id aMaximizar = null;
        Caracteristica_y_Id noDeseable = null;

        switch (opc) {
            case 1:
                System.out.println("Ingrese el índice de la característica de personalidad deseada:");
                int indexMaximizarBf5 = scanner.nextInt();
                aMaximizar = new Caracteristica_y_Id(Caractistica.BigFiveScores, AtributoBf5.values()[indexMaximizarBf5]);
                break;
            case 2:
                System.out.println("Ingrese el índice del interés académico deseado:");
                int indexMaximizarInteres = scanner.nextInt();
                aMaximizar = new Caracteristica_y_Id(Caractistica.InteresesAcademicos, AtributosInteresesAcade.values()[indexMaximizarInteres]);
                break;
            default:
                throw new IllegalArgumentException("Opción no válida");
        }

        System.out.println("Ingrese la característica que desea minimizar entre sus recomendados");
        System.out.println("1 para características de personalidad, 2 para intereses académicos: ");
        opc = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        int maxCriterioLimitePorAlumno = 1;
        switch (opc) {
            case 1:
                System.out.println("Ingrese el índice de la característica de personalidad no deseada:");
                int indexNoDeseableBf5 = scanner.nextInt();
                noDeseable = new Caracteristica_y_Id(Caractistica.BigFiveScores, AtributoBf5.values()[indexNoDeseableBf5]);
                System.out.println("Ingrese el maximo criterio limite (un numero entre 1 a 20 para personalidad): ");
                maxCriterioLimitePorAlumno = scanner.nextInt();
                break;
            case 2:
                System.out.println("Ingrese el índice del interés académico no deseado:");
                int indexNoDeseableInteres = scanner.nextInt();
                noDeseable = new Caracteristica_y_Id(Caractistica.InteresesAcademicos, AtributosInteresesAcade.values()[indexNoDeseableInteres]);
                System.out.println("Ingrese el maximo criterio limite (un numero entre 1 a 10 para intereses academicos): ");
                maxCriterioLimitePorAlumno = scanner.nextInt();
                break;
            default:
                throw new IllegalArgumentException("Opción no válida");
        }

        AlumnoService alumnoService = new AlumnoService();
        NetworkService networkService = new NetworkService();
        long inicio = System.nanoTime();
        List<Alumno> todosLosAlumnosDeFacultad = alumnoService.todosLosAlumnosDeFacultad(nombreFacultad, universidad);
        LinkedList<Alumno> filtrados = networkService.filtrarAlumnos(todosLosAlumnosDeFacultad, aMaximizar, noDeseable, todosLosAlumnosDeFacultad.size() / 10, maxCriterioLimitePorAlumno);
        LinkedList<Alumno> seleccionados = networkService.recomendadosPostFiltro(alumno, filtrados, 6);
        long fin = System.nanoTime();
        long tiempoTranscurrido = fin - inicio;
        // Mostrar el tiempo transcurrido en milisegundos
        System.out.println("Tiempo transcurrido: " + tiempoTranscurrido / 1_000_000.0 + " milisegundos");
        System.out.println("Se procesaron :  " + todosLosAlumnosDeFacultad.size() + " alumnos");
        System.out.println("Compañeros recomendados:");
        for (Alumno filtrado : seleccionados) {
            System.out.println(filtrado.getPrimerNombre() + " " + filtrado.getPrimerApellido() + " Escuela profesional: "+filtrado.getEp().getNombre() + "     ID del compañero recomendado: " + filtrado.getCodigo());
        }
    }
}
