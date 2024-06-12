/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.domain;

import com.killa.sierravp.util.TipoNota;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import java.io.Serializable;

/**
 *
 * @author karlo
 */
@Entity
public class Nota implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "codigo_alumno", referencedColumnName = "codigo", nullable = false)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoNota tipo;
    
    @Column(nullable = false)
    private int calificacion;
    
    @Column(nullable = false)
    private double valor;

    public Nota() {
    }

    public TipoNota getTipo() {
        return tipo;
    }

    public void setTipo(TipoNota tipo) {
        this.tipo = tipo;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
