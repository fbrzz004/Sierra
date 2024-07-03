/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.client;

import com.killa.sierravp.repository.Universidad;
import com.killa.sierravp.util.LoginSystem;

/**
 *
 * @author HITV
 */
public class SIERRAVPMenu {

    public static void main(String[] args) {

        Universidad universidad = GenerarFacultades.GenerarFacultadesCompletas("Facultad de Ciencias Físicas");

        LoginSystem loginSystem = new LoginSystem(universidad);

        loginSystem.iniciar();
    }

}
