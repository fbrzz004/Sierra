/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.util;

/**
 *
 * @author karlo
 */

public class CodigoGeneratorUsuario {

    private static int currentCode = 0;

    public synchronized static int generate() {
        currentCode++;
        return currentCode;
    }
}
