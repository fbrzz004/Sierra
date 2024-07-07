package com.killa.sierravp.domain;

import jakarta.persistence.*;

@Entity
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private int posicion;

    @ManyToOne
    @JoinColumn(name = "alumno_id", referencedColumnName = "codigo", nullable = false)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "escuela_id", referencedColumnName = "id", nullable = false)
    private EscuelaProfesional escuela;

    public Ranking() {
    }

    public Ranking(int posicion, Alumno alumno, EscuelaProfesional escuela) {
        this.posicion = posicion;
        this.alumno = alumno;
        this.escuela = escuela;
    }

    public int getId() {
        return id;
    }

    public int getPosicion() {
        return posicion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public EscuelaProfesional getEscuela() {
        return escuela;
    }
}
