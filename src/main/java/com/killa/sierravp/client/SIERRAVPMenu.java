/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Curso;
import com.killa.sierravp.domain.Usuario;
import com.killa.sierravp.repository.Universidad;
import com.killa.sierravp.repository.Universidad.FacultadData;
import com.killa.sierravp.service.NotaService;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 *
 * @author HITV
 */
public class SIERRAVPMenu {
    
    private static Scanner scanner = new Scanner(System.in);
    private static NotaService notaService;

    public static void main(String[] args) {
        String nombreFacultad = "Facultad de Derecho y Ciencia Política";
        Universidad universidad = GenerarFacultades.GenerarFacultadesCompletas(nombreFacultad);
        notaService = new NotaService(universidad);
        
        FacultadData fd = universidad.obtenerFacultad(nombreFacultad);
        
        Map<Integer, Universidad.EscuelaData> escuelas = fd.getEscuelas();
        System.out.println("Escuelas disponibles:");
        for (Map.Entry<Integer, Universidad.EscuelaData> entry : escuelas.entrySet()) {
            Integer key = entry.getKey();
            Universidad.EscuelaData value = entry.getValue();
            System.out.println("Id[" + key + "] Escuela: " + value.getEscuela().getNombre());
        }
        
        System.out.print("Seleccione una escuela (número): ");
        int idEscuela = scanner.nextInt();
        
        Universidad.EscuelaData escuelaElegida = escuelas.get(idEscuela);
        
        System.out.print("Digite la posición que requiere buscar: ");
        int posicionK = scanner.nextInt();
        
        List<Alumno> alumnos = escuelaElegida.getAlumnos();
        //System.out.println("Cursos disponibles:");
        for(Curso curso: escuelaElegida.getCursos()) {
            //System.out.println("== Curso con Id [" + curso.getId() + "]");        
            Alumno alumnoK = notaService.obtenerKenesimoAlumnoPorNotaFinal(alumnos, curso.getId(), posicionK);
                if (alumnoK != null) {
                    System.out.println("El alumno: " + alumnoK.getNombre() + " " + alumnoK.getPrimerApellido() + " obtuvo la posición numero [" + posicionK + "] para el curso [" + curso.getId()+ "]");
                } 
        }
    }

}
