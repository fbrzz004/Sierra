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
import com.killa.sierravp.service.AlumnoService;
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
    private static AlumnoService alumnoService;

    public static void main(String[] args) {
        //OJO EN el menu tienes que llamar a la clase Universidad y pasarle una facultad comom parametro
        String nombreFacultad = "Facultad de Derecho y Ciencia Política";
        Universidad universidad = GenerarFacultades.GenerarFacultadesCompletas(nombreFacultad);
        notaService = new NotaService(universidad);
        alumnoService = new AlumnoService(universidad);
        
        FacultadData fd = universidad.obtenerFacultad(nombreFacultad);
        
        //elegir una escuela
        Map<Integer, Universidad.EscuelaData> escuelas = fd.getEscuelas();
        System.out.println("Escuelas disponibles:");
        for (Map.Entry<Integer, Universidad.EscuelaData> entry : escuelas.entrySet()) {
            Integer key = entry.getKey();
            Universidad.EscuelaData value = entry.getValue();
            System.out.println("Id[" + key + "] Escuela: " + value.getEscuela().getNombre());
        }
        
        //de la escuela elegida, selecciona 1 curso
        System.out.print("Seleccione una escuela (número): ");
        int idEscuela = scanner.nextInt();
        
        Universidad.EscuelaData escuelaElegida = escuelas.get(idEscuela);
        
        System.out.println("Cursos disponibles:");
        for(Curso curso: escuelaElegida.getCursos()) {
            System.out.println("Id[" + curso.getId() + "] Curso: " + curso);
        }
        
        System.out.print("Seleccione un curso (número): ");
        int idCurso = scanner.nextInt();
        
        List<Alumno> alumnos = escuelaElegida.getAlumnos();
        //alumnos.stream().forEach(al -> {System.out.println("alumno " + al.getNombre());});
        
        System.out.print("Digite la posición que requiere buscar: ");
        int posicionK = scanner.nextInt();
        
        Alumno alumnoK = notaService.obtenerKesimoAlumnoPorNotaFinal(alumnos, idCurso, posicionK);
        if (alumnoK == null) {
            System.out.println("Alumno no encontrado");
        } else {
            System.out.print("El alumno: " + alumnoK + "obtuvo la posición numero " + posicionK);        
        }
    }

}
