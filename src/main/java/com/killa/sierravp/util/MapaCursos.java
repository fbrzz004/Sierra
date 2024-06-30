/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author karlo
 */
public class MapaCursos {
    public Map<KeyMapaCursos,String> map = new HashMap<>();
    
    public void put(String Facultad, int ciclo, int numCurso,String Curso){
        map.put(new KeyMapaCursos(Facultad, ciclo, numCurso), Curso);
    }
    
    public String get(String Facultad, int ciclo, int numCurso){
        return map.get(new KeyMapaCursos(Facultad,ciclo,numCurso));
    }
}
