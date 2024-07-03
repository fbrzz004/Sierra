/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.util;

import java.util.Map;

/**
 *
 * @author HITV
 */
public class probandoCursoFacultas {
    public static void main(String[] args) {
        MapaCursos mc = ObtenerCursos.obtenerCursosDeFacultad("FDok2.txt");
        int i=0;
        for (Map.Entry<KeyMapaCursos, String> en : mc.map.entrySet()) {
            System.out.println(en.getKey().toString());
            String curso = en.getValue();
            System.out.println(curso);
            i=i+1;
        }
        System.out.println(i);
    }
}