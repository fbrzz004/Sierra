/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 *
 * @author karlo
 */

//Ya esta el mapeo bidireccional de Facultad con alumno, y EP
@Entity
public class Facultad {
    @Id
    private int id;
    private String nombre;
    @OneToMany(mappedBy = "facultad")
    private List<Alumno> alumnos;
    @OneToMany(mappedBy = "facultad")
    private EscuelaProfesional ep;
    
    public Facultad() {
    }

    public Facultad(int id, String nombre, List<Alumno> alumnos) {
        this.id = id;
        this.nombre = nombre;
        this.alumnos = alumnos;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public EscuelaProfesional getEp() {
        return ep;
    }

    public void setEp(EscuelaProfesional ep) {
        this.ep = ep;
    }
    
}
