/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.util;

/**
 *
 * @author Leonid
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CodigoCursoGenerator {

    private static int currentCode;

    static {
        String filePath = "cursos/codigo_curso.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            currentCode = line != null ? Integer.parseInt(line) : 100000;
        } catch (IOException e) {
            e.printStackTrace();
            currentCode = 100000;
        }
    }

    public synchronized static int generate() {
        currentCode++;
        saveCurrentCode();
        return currentCode;
    }

    private static void saveCurrentCode() {
        String filePath = "cursos/codigo_curso.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(Integer.toString(currentCode));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
