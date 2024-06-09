/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.io.Serializable;

/**
 *
 * @author karlo
 */
//Ya esta relacionado con Alumno y su relacion es unidireccional solo Rank la tiene, porque 
//que el alum tenga tambien conocimiento de la relacion es inecesario, lo mismo ocurre con facu y escuel Prof.
@Entity
public class Ranking implements Serializable { //EL ranking calcula el periodo de matricula basandose
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "fac_id", referencedColumnName = "id")
    private Facultad facultad;
    @ManyToOne
    @JoinColumn(name = "ep_id", referencedColumnName = "id")
    private EscuelaProfesional escuelaProfesional;
    @OneToOne
    @JoinColumn(name = "codigo_alumno", referencedColumnName = "codigo")
    private Alumno alumno;
    @Column(nullable = false)
    private int posicion;

    public Ranking() { //no borrar los constructores vacios, son necesarios para el ORM
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public EscuelaProfesional getEscuelaProfesional() {
        return escuelaProfesional;
    }

    public void setEscuelaProfesional(EscuelaProfesional escuelaProfesional) {
        this.escuelaProfesional = escuelaProfesional;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
}
