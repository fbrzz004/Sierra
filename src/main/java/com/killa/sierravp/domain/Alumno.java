/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.Set;

/**
 *
 * @author karlo
 */
@Entity
@DiscriminatorValue("ALUMNO")
public class Alumno extends Usuario {

    @Column(unique = true)
    private int codigo;
    @ManyToOne
    @JoinColumn(name = "facultad_id",referencedColumnName = "id")
    private Facultad facultad;
    @ManyToOne
            @JoinColumn(name = "ep_id",referencedColumnName = "id")
    private EscuelaProfesional ep;
    @ManyToMany
    @JoinTable(
            name = "alumnos_clases",
            joinColumns = @JoinColumn(name = "codigo_alumno",referencedColumnName = "codigo"),
            inverseJoinColumns = @JoinColumn(name = "curso_id", referencedColumnName = "id"))
    private Set<Clase> clases;
    @OneToOne(mappedBy = "alumno")
    private Alumno alumno;
    
    private boolean regular;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public EscuelaProfesional getEp() {
        return ep;
    }

    public void setEp(EscuelaProfesional ep) {
        this.ep = ep;
    }

    public Set<Clase> getClases() {
        return clases;
    }

    public void setClases(Set<Clase> clases) {
        this.clases = clases;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public boolean isRegular() {
        return regular;
    }

    public void setRegular(boolean regular) {
        this.regular = regular;
    }
}   
