/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.client.localRepository;

import com.killa.sierravp.domain.EscuelaProfesional;
import com.killa.sierravp.domain.Facultad;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author HITV
 */
public class FacultadesEscuelas {

    public static Map<String, Set<?>> obtenerFacultadesYEps() {
        Map<Integer, Facultad> facultadesMap = new HashMap<>();
        HashSet<EscuelaProfesional> eps = new HashSet<>(70);
        String filePath = "data/Facultades_Escuelas.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] partes = line.split(",");
                String nombreEscuela = partes[0].trim();
                int idEscuela = Integer.parseInt(partes[1].trim());
                String nombreFacultad = partes[2].trim();
                int idFacultad = Integer.parseInt(partes[3].trim());

                Facultad facultad = facultadesMap.computeIfAbsent(idFacultad, k -> new Facultad(idFacultad, nombreFacultad));
                EscuelaProfesional escuelaProfesional = new EscuelaProfesional(idEscuela, nombreEscuela, facultad);
                eps.add(escuelaProfesional);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Convertir el Map de facultades a un HashSet
        HashSet<Facultad> facultades = new HashSet<>(facultadesMap.values());
        // Crear el resultado como un Map
        Map<String, Set<?>> resultado = new HashMap<>();
        resultado.put("facultades", facultades);
        resultado.put("eps", eps);
        return resultado;
    }
    
    public static Facultad buscarFacultadPorNombre(Set<Facultad> facultades, String nombre) {
        for (Facultad facultad : facultades) {
            if (facultad.getNombre().equalsIgnoreCase(nombre)) {
                return facultad;
            }
        }
        return null; 
    }

    public static Facultad buscarFacultadPorId(Set<Facultad> facultades, int id) {
        for (Facultad facultad : facultades) {
            if (facultad.getId() == id) {
                return facultad;
            }
        }
        return null; 
    }
    
    public static EscuelaProfesional buscarEpPorNombre(Set<EscuelaProfesional> eps, String nombre) {
        for (EscuelaProfesional ep : eps) {
            if (ep.getNombre().equalsIgnoreCase(nombre)) {
                return ep;
            }
        }
        return null; 
    }

    public static EscuelaProfesional buscarEpPorId(Set<EscuelaProfesional> eps, int id) {
        for (EscuelaProfesional ep : eps) {
            if (ep.getId() == id) {
                return ep;
            }
        }
        return null; 
    }
}