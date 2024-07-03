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

    static {
        String filePath = "nombres/codigo.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            currentCode = line != null ? Integer.parseInt(line) : 22222222;
        } catch (IOException e) {
            e.printStackTrace();
            currentCode = 22222222;
        }
    }

    public synchronized static int generate() {
        currentCode++;
        saveCurrentCode();
        return currentCode;
    }

    public static void saveCurrentCode() {
        String filePath = "nombres/codigo.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(Integer.toString(currentCode));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
