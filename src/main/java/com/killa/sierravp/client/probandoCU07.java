/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.BigFiveScores;
import com.killa.sierravp.domain.EscuelaProfesional;
import com.killa.sierravp.domain.Facultad;
import com.killa.sierravp.domain.InteresesAcademicos;
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
import java.util.Scanner;

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
        //ep
        EscuelaProfesional ep1 = new EscuelaProfesional();
        ep1.setNombre("Ing Software");
        ep1.setFacultad(facultad);
        lista.add(ep1);
        eps.create(ep1);
        EscuelaProfesional ep2 = new EscuelaProfesional();
        ep2.setNombre("Ing Sistemas");
        ep2.setFacultad(facultad);
        lista.add(ep2);
        eps.create(ep2);
        //facultad
        facultad.setNombre("FISI");
        facultad.setEp(lista);
        facultadService.create(facultad);
        Alumno alum = new Alumno();
        //genero data para la comparacion
        for (int i = 0; i < 1000; i++) {
            alum.setPrimerNombre("a"+i+1);
            alum.setBfScores(new BigFiveScores());
            alum.setInteresesAcademicos(new InteresesAcademicos());
            alum.setFacultad(facultad);
            if (i%2==0) { //par le asigno cofware
                alum.setEp(ep1);
            }
            else{
                alum.setEp(ep2);
            }
            alumnoService.create(alum);
        }

        /* para probar por consola
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su id de alumno xd: ");
        int id= scanner.nextInt();
        scanner.nextLine();
        */
        //Modulo alumno Social
        //aqui en codigo digo que quiero que sea favorecido y que no por mi metodo, para no demorarme haciendo por consola
        Caracteristica_y_Id aMaximizar = new Caracteristica_y_Id(Caractistica.BigFiveScores, AtributoBf5.altruismo);
        Caracteristica_y_Id noDeseable = new Caracteristica_y_Id(Caractistica.InteresesAcademicos, AtributosInteresesAcade.trabajoEmpresarial);
        //recupero los alumnos de la bdd
        LinkedList<Alumno> AllalumnosFacultad = new LinkedList<>(alumnoService.allAlumnosFromFacultad(facultadService.obtenerIdFacultadPorNombre("FISI")));
        //de todos mis alumn selecciono el del medio para que me sirva luego para probar el metodo
        Alumno alumno = AllalumnosFacultad.get((int)AllalumnosFacultad.size()/2);
        LinkedList<Alumno> filtrados = networkService.filtrarAlumnos(AllalumnosFacultad, aMaximizar, noDeseable, AllalumnosFacultad.size()/10,5 );
        LinkedList<Alumno> selecionados = networkService.recomendadosPostFiltro(alumnoService.findByCodigo(alumno.getCodigo()), filtrados,6);
        for (Alumno filtrado : selecionados) {
            System.out.println("el id de su compañero recomendado es "+filtrado.getPrimerNombre());
        }
    }
    
    public void menu(){
        
    }
}
