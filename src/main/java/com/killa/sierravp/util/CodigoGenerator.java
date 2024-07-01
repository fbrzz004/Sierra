/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.util;

/**
 *
 * @author karlo
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CodigoGenerator {

    private static int currentCode;

    // Inicializa el valor de currentCode leyendo del archivo de texto
    static {
        String filePath = "nombres/" + "codigo.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line != null) {
                currentCode = Integer.parseInt(line);
            } else {
                currentCode = 22222222; // valor por defecto si el archivo está vacío
            }
        } catch (IOException e) {
            e.printStackTrace();
            currentCode = 22222222; // valor por defecto en caso de error de lectura
        }
    }

    public synchronized static int generate() {
        currentCode = currentCode + 1;
        return currentCode;
    }

    // Guarda el último valor de currentCode en el archivo de texto
    public static void saveCurrentCode() {
        String filePath = "nombres/" + "codigo.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(Integer.toString(currentCode));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}