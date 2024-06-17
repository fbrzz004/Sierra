package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.util.PuntajesComparator;
import com.killa.sierravp.util.puntajeAlumno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author karlo
 */
public class NetworkService {

    private static EntityManagerFactory emf;

    public NetworkService() {
        emf = Persistence.createEntityManagerFactory("UnidadPersistencia");
    }

    public List<Alumno> allAlumnosFromFacultad(int facultadID) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Alumno> query= em.createQuery("SELECT alumnos FROM Alumno alumnos WHERE alumnos.facultad.id =:facultadID",Alumno.class);
            query.setParameter("facultadID",facultadID);
            return query.getResultList();
        } finally{
            em.close();
        }
    }
    
    //este metodo se utiliza despues del filtro donde se selecciona los posibles mejores 
    //compañeros antes de realizar el calculo de similitud coseno
    public LinkedList<Alumno> recomendadosPostFiltro(Alumno alumno,List<Alumno> alumnosFiltrados, int cantidadCompañeros) { 
        BigFiveScoresService bfs = new BigFiveScoresService();
        InteresesAcademicosService ias = new InteresesAcademicosService();
        LinkedList<Alumno> seleccionados= new LinkedList<>();
        PuntajesComparator pc = new PuntajesComparator(); //orden descendente el primero es el mayor
        TreeSet<puntajeAlumno> alumnosAndPuntaje = new TreeSet(pc);
        
        float similitudCosenoBfs;
        float similitudCosenoIas;
        for (Alumno alumnosFiltrado : alumnosFiltrados) {
            similitudCosenoBfs = bfs.similitudCoseno(alumno.getCodigo(), alumnosFiltrado.getCodigo());
            similitudCosenoIas = ias.similitudCoseno(alumno.getCodigo(), alumnosFiltrado.getCodigo());
            //agrega un alumno con su puntaje promediado de simulitud entre los 2 criterios
            alumnosAndPuntaje.add(new puntajeAlumno(alumnosFiltrado,(similitudCosenoBfs+similitudCosenoIas)/2));
        }
        for (int i = 0; i < cantidadCompañeros && !seleccionados.isEmpty(); i++) {
            seleccionados.add(alumnosAndPuntaje.pollLast().getAlumno());
        }
        return seleccionados;
    }
}
