package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.util.CantidadIndex;
import com.killa.sierravp.util.Caracteristica_y_Id;
import com.killa.sierravp.util.PuntajesComparator;
import com.killa.sierravp.util.puntajeAlumno;
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

    //este metodo se utiliza despues del filtro donde se selecciona los posibles mejores 
    //compañeros antes de realizar el calculo de similitud coseno
    public LinkedList<Alumno> recomendadosPostFiltro(Alumno alumno, List<Alumno> alumnosFiltrados, int cantidadCompañeros) {
        BigFiveScoresService bfs = new BigFiveScoresService();
        InteresesAcademicosService ias = new InteresesAcademicosService();
        LinkedList<Alumno> seleccionados = new LinkedList<>();
        PuntajesComparator pc = new PuntajesComparator(); //orden descendente el primero es el mayor
        TreeSet<puntajeAlumno> alumnosAndPuntaje = new TreeSet(pc);
        //si mi alumno solicitante se encuentra en la lista de sus filtrados se borra
        if (alumnosFiltrados.contains(alumno)) {
            alumnosFiltrados.remove(alumno);
        }
        float similitudCosenoBfs;
        float similitudCosenoIas;
        for (Alumno alumnosFiltrado : alumnosFiltrados) {
            similitudCosenoBfs = bfs.similitudCoseno(alumno, alumnosFiltrado);
            similitudCosenoIas = ias.similitudCoseno(alumno, alumnosFiltrado);
            //agrega un alumno con su puntaje promediado de simulitud entre los 2 criterios
            alumnosAndPuntaje.add(new puntajeAlumno(alumnosFiltrado, (similitudCosenoBfs + similitudCosenoIas) / 2));
        }
        for (int i = 0; i < cantidadCompañeros && !alumnosAndPuntaje.isEmpty(); i++) { //aqui garantizo que se detendra si obtengo los compañeros o el arbolito esta vacio
            seleccionados.add(alumnosAndPuntaje.pollLast().getAlumno());
        }
        return seleccionados;
    }

    //maxCriterioLimite es el valor que ingresa el maximo permitido para una
    //persona el cual luego se multiplica por maxAlumnos para obtener el limite total
    //sobre el que trabaja el metodo
    public LinkedList<Alumno> filtrarAlumnos(List<Alumno> alumnos, Caracteristica_y_Id aMaximizar, int maxAlumnos,
            Caracteristica_y_Id noDeseable, int maxCriterioLimitePorAlum) {

        int maxCriterioLimite = maxCriterioLimitePorAlum * maxAlumnos;
        int n = alumnos.size();
        int[][] dp = new int[n + 1][maxCriterioLimite + 1];
        boolean[][] keep = new boolean[n + 1][maxCriterioLimite + 1];
        LinkedList<Alumno> resultado = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            Alumno alumno = alumnos.get(i - 1);
            int valor = alumno.procesarCaracte_Id(aMaximizar);
            int peso = alumno.procesarCaracte_Id(noDeseable);

            for (int w = 0; w <= maxCriterioLimite; w++) { // Comprobar si el peso se ajusta a w
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
        return resultado;
    }

    //Metodos de apoyo para filtrar
    public int contarElementos(boolean[][] trakeo, int[][] dp, int columna, int fila, List<Alumno> alumnos, Caracteristica_y_Id noDeseable) {
        int contador = 0;
        while (columna > 0 && fila > 0) {
            //la forma buena de desplazarse cuando hay un true es como si fuera un caballito wiii
            if (trakeo[fila][columna]) { //ojo aqui repite cuentas tengo que realizar validacion para que no repita la cuenta si se trata de un i anterior
                contador++;
                int peso = alumnos.get(fila - 1).procesarCaracte_Id(noDeseable); //me desplazo segun el valor del no deseable, antes lo hacia siguiendo su deseable tal que malo pe
                columna -= peso; //disminuyo el valor de no deseable de mi alumno seleccionado de la columna actual
            }
            fila--;
        }
        return contador;
    }

    public int ObtenerMejorIndice(CantidadIndex[] registro, int ultimoIndex, int maxAlumnos) {
        int mejorIndex = 0;
        //Comparo cual es mas optimo si registro[0] o registro[1] es decir cual me un valor absoluto mas cercano a 0
        if (ultimoIndex % 2 == 1) { //ultimo es decir el mas actual fue impar esto significa que su "peso es mayor"
            if (Math.abs(maxAlumnos - registro[0].getCantidad()) <= Math.abs(maxAlumnos - registro[1].getCantidad())) {
                //el fi lo que hace es ver si el anterior tiene una distacia igual o mas cercana a el max
                //si es asi (osea igual) lo preferimos para la asignacion porque su valor de caracteristica no deseada es menor
                mejorIndex = registro[0].getIndex();
            } else {
                mejorIndex = registro[1].getIndex();
            }
        } else { //osea el ultimo indice de mi for fue par
            if (Math.abs(maxAlumnos - registro[1].getCantidad()) <= Math.abs(maxAlumnos - registro[0].getCantidad())) {
                //en este caso r[1] representa el reg anterior, si el anterior cumple con estar mas cerca o ser igual a las distancia del actual
                //se toma este por tener un menor acumulado de la caracteristica no deseada
                mejorIndex = registro[1].getIndex();
            } else {
                mejorIndex = registro[0].getIndex();
            }
        }
        return mejorIndex;
    }
}
