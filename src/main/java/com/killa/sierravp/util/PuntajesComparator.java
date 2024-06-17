/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.util;

import java.util.Comparator;

/**
 *
 * @author karlo
 */
public class PuntajesComparator implements Comparator<puntajeAlumno>{

    @Override
    public int compare(puntajeAlumno o1, puntajeAlumno o2) {
        return Float.compare(o2.getPuntaje(), o1.getPuntaje());
    }
    
}
