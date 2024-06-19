/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;

/**
 *
 * @author karlo
 */
@Entity
public class CRA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int craId;

    @ManyToOne
    @JoinColumn(name = "codigo_alumno", referencedColumnName = "codigo", nullable = false)
    private Alumno alumno;

    @Column(nullable = false)
    private String periodo;

    @Column(nullable = false)
    private double cra;

    public CRA() {
    }

    public int getCraId() {
        return craId;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public double getCra() {
        return cra;
    }

    public void setCra(double cra) {
        this.cra = cra;
    }

}
