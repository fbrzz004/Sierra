package com.killa.sierravp.domain;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Ciclo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "escuela_profesional_id", referencedColumnName = "id", nullable = false)
    private EscuelaProfesional escuelaProfesional;

    @OneToMany(mappedBy = "ciclo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Clase> clases;

    public Ciclo() {
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
