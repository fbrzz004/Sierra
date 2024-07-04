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
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author karlo
 */

//Facultad ya esta OK no conoce sus alum ni ranking pero estos si saben a que fac pertenecen
@Entity
public class Facultad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String nombre;
    @OneToMany(mappedBy = "facultad")
    private Set <EscuelaProfesional> ep;
    
    public Facultad() {
    }


    public Facultad(int idFacultad, String nombreFacultad) {
        this.id = idFacultad;
        this.nombre = nombreFacultad;
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

    public Set <EscuelaProfesional> getEp() {
        return ep;
    }

    public void setEp(Set <EscuelaProfesional> ep) {
        this.ep = ep;
    }

}