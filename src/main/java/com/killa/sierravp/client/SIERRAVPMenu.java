/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.client;

import com.killa.sierravp.repository.Universidad;


/**
 *
 * @author HITV
 */
public class SIERRAVPMenu {

    public static void main(String[] args) {
        //OJO EN el menu tienes que llamar a la clase Universidad y pasarle una facultad comom parametro
        Universidad universidad = GenerarFacultades.GenerarFacultadesCompletas("Facultad de Medicina Humana");

    }

}
