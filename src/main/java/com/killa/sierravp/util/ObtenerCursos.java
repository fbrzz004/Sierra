/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author karlo
 */
public class ObtenerCursos {
    public static MapaCursos obtenerCursosDeFacultad(String nomArchivoCursosFacultad){
        MapaCursos mapaCursos = new MapaCursos();
        String filePath = "nombres/" + nomArchivoCursosFacultad;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                procesarLinea(linea, mapaCursos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        para probar
         // Ejemplo de cómo obtener un curso
        System.out.println(mapaCursos.get("Facultad de Medicina", 7, 2)); // Debería imprimir Pediatría III
        //Derecho Constitucional Comparado
        System.out.println(mapaCursos.get("Facultad de Derecho y Ciencia Política", 9, 5)); // Debería imprimir Gineco-Obstetricia V 
        */
        return mapaCursos;
        
    }

    private static void procesarLinea(String linea, MapaCursos mapaCursos) {
        String[] partes = linea.split(":");
        String facultad = partes[0].trim();
        String[] cursosConCiclo = partes[1].split(",");
        int cicloActual = 0;
        int numeroCurso = 1;

        for (String cursoConCiclo : cursosConCiclo) {
            cursoConCiclo = cursoConCiclo.trim();
            String nombreCurso = cursoConCiclo.substring(0, cursoConCiclo.lastIndexOf('(')).trim();
            int ciclo = Integer.parseInt(cursoConCiclo.substring(cursoConCiclo.lastIndexOf('(') + 1, cursoConCiclo.lastIndexOf(')')));
            if (ciclo != cicloActual) { //cuando se ejecuta esto es porque proceso el inicio de la linea
                cicloActual = ciclo;
                //si no hay registros de curso todavia en ese ciclo le asigno el 1 a num curso
                if (mapaCursos.get(facultad, ciclo, numeroCurso) == null) {
                    numeroCurso = 1;
                } else {
                    int contador = 2;
                    //mientras haya registros que aumente el contador
                    while (mapaCursos.get(facultad, ciclo, contador) != null) {
                        contador++;
                    }
                    numeroCurso = contador; //el primer num curso libre
                }
            }

            mapaCursos.put(facultad, ciclo, numeroCurso, nombreCurso);
            numeroCurso++;
        }
    }
}
