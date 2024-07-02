/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.client.localRepository;

import com.killa.sierravp.domain.EscuelaProfesional;
import com.killa.sierravp.domain.Facultad;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author HITV
 */
public class comoUsarMetodosParaRecuperarFacuYEp {
    public static void main(String[] args) {
        Map<String, Set<?>> resultado = FacultadesEscuelas.obtenerFacultadesYEps();

        Set<Facultad> facultades = (Set<Facultad>) resultado.get("facultades");
        Set<EscuelaProfesional> eps = (Set<EscuelaProfesional>) resultado.get("eps");

        // Ahora puedes usar los conjuntos de facultades y escuelas profesionales
        System.out.println("Facultades:");
        for (Facultad facultad : facultades) {
            System.out.println(facultad.getNombre());
        }

        System.out.println("\nEscuelas Profesionales:");
        for (EscuelaProfesional ep : eps) {
            System.out.println(ep.getNombre());
        }
    }
}