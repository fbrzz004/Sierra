package com.killa.sierravp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

@Entity
public class Clase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany(mappedBy = "clases")
    private Set<Alumno> alumnos;

    @ManyToOne
    @JoinColumn(name = "profesor_id", referencedColumnName = "DNI", nullable = false)
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @OneToMany(mappedBy = "clase")
    private List<Nota> notas;

    @OneToMany(mappedBy = "clase")
    private List<CRA> cras;

    // Constructor vacío requerido por JPA
    public Clase() {
        this.notas = new ArrayList<>();
        this.cras = new ArrayList<>();
    }

    // Constructor para inicializar una clase con un curso
    public Clase(Curso curso) {
        this();
        this.curso = curso;
    }

    // Getters y setters para los atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Set<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public List<CRA> getCRAs() {
        return cras;
    }

    public void setCRAs(List<CRA> cras) {
        this.cras = cras;
    }

    // Método para agregar un CRA a la lista de CRAs de la clase
    public void agregarCRA(CRA cra) {
        this.cras.add(cra);
    }
}
