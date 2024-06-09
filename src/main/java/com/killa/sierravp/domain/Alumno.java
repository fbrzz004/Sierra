/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.Set;

/**
 *
 * @author karlo
 */
@Entity
@DiscriminatorValue("ALUMNO")
public class Alumno extends Usuario {

    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;

    @ManyToOne
    @JoinColumn(name = "facultad_id", referencedColumnName = "id", nullable = false)
    private Facultad facultad;

    @ManyToOne
    @JoinColumn(name = "ep_id", referencedColumnName = "id", nullable = false)
    private EscuelaProfesional ep;

    @ManyToMany
    @JoinTable(
            name = "alumnos_clases",
            joinColumns = @JoinColumn(name = "codigo_alumno", referencedColumnName = "codigo"),
            inverseJoinColumns = @JoinColumn(name = "curso_id", referencedColumnName = "id"))
    private Set<Clase> clases;

    @OneToOne(mappedBy = "alumno")
    private Ranking ranking;

    @OneToMany(mappedBy = "alumno")
    private Set<Nota> notas;

    private boolean regular;

    @OneToMany(mappedBy = "alumno")
    private Set<CRA> craHistorico;
    
    public Alumno() {
    }

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

    public boolean isRegular() {
        return regular;
    }

    public void setRegular(boolean regular) {
        this.regular = regular;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    public Set<Nota> getNotas() {
        return notas;
    }

    public void setNotas(Set<Nota> notas) {
        this.notas = notas;
    }

}
