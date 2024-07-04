package com.killa.sierravp.domain;

import java.util.Set;

public class Ciclo {

    private int id;
    private String nombre;
    private EscuelaProfesional escuelaProfesional;
    private Set<Clase> clases;

    
    public Ciclo() {
    }

    // Getters y setters para acceder y modificar los atributos
    
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

    public EscuelaProfesional getEscuelaProfesional() {
        return escuelaProfesional;
    }

    public void setEscuelaProfesional(EscuelaProfesional escuelaProfesional) {
        this.escuelaProfesional = escuelaProfesional;
    }

    public Set<Clase> getClases() {
        return clases;
    }

    public void setClases(Set<Clase> clases) {
        this.clases = clases;
    }
}
