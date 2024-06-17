/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.util;

import com.killa.sierravp.domain.Alumno;

/**
 *
 * @author karlo
 */
public class puntajeAlumno {
    private Alumno alumno;
    private float puntaje;

    public puntajeAlumno(Alumno alumno, float puntaje) {
        this.alumno = alumno;
        this.puntaje = puntaje;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public float getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(float puntaje) {
        this.puntaje = puntaje;
    }


}
