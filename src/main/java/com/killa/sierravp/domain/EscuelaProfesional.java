package com.killa.sierravp.domain;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class EscuelaProfesional {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "facultad_id", referencedColumnName = "id", nullable = false)
    private Facultad facultad;

    @OneToMany(mappedBy = "escuelaProfesional", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ciclo> ciclos;

    public EscuelaProfesional() {
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

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Set<Ciclo> getCiclos() {
        return ciclos;
    }

    public void setCiclos(Set<Ciclo> ciclos) {
        this.ciclos = ciclos;
    }
}
