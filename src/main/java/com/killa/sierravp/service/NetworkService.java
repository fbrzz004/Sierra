package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.util.Caracteristica_y_Id;
import com.killa.sierravp.util.PuntajesComparator;
import com.killa.sierravp.util.puntajeAlumno;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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

        //maxCriterioLimite es el valor que ingresa el maximo permitido para una
        //persona el cual luego se multiplica por maxAlumnos para obtener el limite total
        //sobre el que trabaja el metodo
        public LinkedList<Alumno> filtrarAlumnosMejorado(List<Alumno> alumnos,Caracteristica_y_Id aMaximizar,
                Caracteristica_y_Id noDeseable, int maxAlumnos, int maxCriterioLimitePorAlum) {
        //aMaximizar haria de valor en el problema de la mochila y noDeseable seria el peso
        int maxCriterioLimite=maxCriterioLimitePorAlum*maxAlumnos;
        int n = alumnos.size();
        int[][] dp = new int[n + 1][maxCriterioLimite + 1];
        boolean[][] keep = new boolean[n + 1][maxCriterioLimite + 1];
        LinkedList<Alumno> resultado = new LinkedList<>();

        // Inicializar las matrices, NO se HACE PORQUE ES EL VALOR DEFECTO QUE ASIGNA JAVA
        /*
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= maxCriterioLimite; w++) {
                dp[i][w] = 0;
                keep[i][w] = false;
            }
        }
        */

        // Algoritmo de programación dinámica que minimiza la caracteristica no deseable y 
        //optimiza el criterio deseable
        for (int i = 1; i <= n; i++) {
            Alumno alumno = alumnos.get(i - 1);
            int valor = alumno.procesarCaracte_Id(aMaximizar);
            int peso = alumno.procesarCaracte_Id(noDeseable);

            for (int w = 0; w <= maxCriterioLimite; w++) {
                if (peso <= w) {
                    int incluir = valor + dp[i - 1][w - peso];
                    int excluir = dp[i - 1][w];

                    dp[i][w] = Math.max(incluir, excluir);
                    if (incluir > excluir) {
                        keep[i][w] = true;
                    }
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Selección de alumnos de acuerdo a la matriz keep
        int capacidadRestante = maxCriterioLimite;
        for (int i = n; i > 0 && resultado.size() <= maxAlumnos; i--) {
            if (keep[i][capacidadRestante]) {
                resultado.add(alumnos.get(i - 1));
                capacidadRestante -= alumnos.get(i - 1).procesarCaracte_Id(noDeseable);
            }
        }
        return resultado;
    }    
}
