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
        //si pones como llave facultades devuelve las facultades y si pones eps da el conjunto de eps
        Map<String, Set<?>> resultado = FacultadesEscuelas.obtenerFacultadesYEps();
        
        Set<Facultad> facultades = (Set<Facultad>) resultado.get("facultades");
        Set<EscuelaProfesional> eps = (Set<EscuelaProfesional>) resultado.get("eps");
        //facultades.
        // Ahora puedes usar los conjuntos de facultades y escuelas profesionales
        
        
        System.out.println("Facultades:");
        for (Facultad facultad : facultades) {
            System.out.println(facultad.getNombre());
        }
        

        String f = "Facultad de Medicina Veterinaria";
        Facultad buscada = FacultadesEscuelas.buscarFacultadPorNombre(facultades, f);
        System.out.println("");
        if (buscada!=null) {
            System.out.println(buscada.getId());
            for (EscuelaProfesional ep : buscada.getEp()) {
                System.out.println(ep.getNombre());
            }
        }
        else{
            System.out.println("No se encontro fc");
        }

    }
}