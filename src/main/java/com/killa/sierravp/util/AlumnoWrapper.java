/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.util;

import com.killa.sierravp.domain.Alumno;

/**
 *
 * @author GAMA
 */
public class AlumnoWrapper {
    private Alumno alumno;
    private int finalScore;

    public AlumnoWrapper(Alumno alumno, int finalScore) {
        this.alumno = alumno;
        this.finalScore = finalScore;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }
}