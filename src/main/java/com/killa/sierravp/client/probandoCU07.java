/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.EscuelaProfesional;
import com.killa.sierravp.domain.Facultad;
import com.killa.sierravp.service.AlumnoService;
import com.killa.sierravp.service.EscuelaProfesionalService;
import com.killa.sierravp.service.FacultadService;
import com.killa.sierravp.service.NetworkService;
import com.killa.sierravp.util.AtributoBf5;
import com.killa.sierravp.util.AtributosInteresesAcade;
import com.killa.sierravp.util.Caracteristica_y_Id;
import com.killa.sierravp.util.Caractistica;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 *
 * @author karlo
 */
public class probandoCU07 {
    public static void main(String[] args) {
        NetworkService networkService = new NetworkService();
        AlumnoService alumnoService = new AlumnoService();
        FacultadService facultadService = new FacultadService();
        EscuelaProfesionalService eps = new EscuelaProfesionalService();
        LinkedHashSet<EscuelaProfesional> lista = new LinkedHashSet<>();
        Facultad facultad = new Facultad();
       

        Caracteristica_y_Id aMaximizar = new Caracteristica_y_Id(Caractistica.BigFiveScores, AtributoBf5.altruismo);
        Caracteristica_y_Id noDeseable = new Caracteristica_y_Id(Caractistica.InteresesAcademicos, AtributosInteresesAcade.trabajoEmpresarial);
        //recupero los alumnos de la bdd
        LinkedList<Alumno> AllalumnosFacultad = new LinkedList<>(alumnoService.allAlumnosFromFacultad(facultadService.obtenerIdFacultadPorNombre("Facultad de Derecho y Ciencia Política")));
        //de todos mis alumn selecciono el del medio para que me sirva luego para probar el metodo
        Alumno alumno = alumnoService.findByCodigo(888);
        System.out.println(alumno.getPrimerNombre()+" "+alumno.getCorreo());
        
        
        LinkedList<Alumno> filtrados = networkService.filtrarAlumnos(AllalumnosFacultad, aMaximizar, noDeseable, AllalumnosFacultad.size()/10,5 );
        LinkedList<Alumno> selecionados = networkService.recomendadosPostFiltro(alumnoService.findByCodigo(alumno.getCodigo()), filtrados,6);
        for (Alumno filtrado : selecionados) {
            System.out.println("el id de su compañero recomendado es "+filtrado.getPrimerNombre());
        }
        
    }
    
    public void menu(){
        
    }
}
