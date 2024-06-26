/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.Random;

/**
 *
 * @author karlo
 */
@Entity
public class InteresesAcademicos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int investigacion;
    private int trabajoEmpresarial;
    private int emprendimiento;
    private int voluntariado;
    @OneToOne
    @JoinColumn(name = "codigo_alumno", referencedColumnName = "codigo")
    private Alumno alumno;

    public InteresesAcademicos() {
    }

    //generar intereses ramdom
    public InteresesAcademicos(boolean ramdom) {
        Random random = new Random();
        this.investigacion= random.nextInt(11);
        this.trabajoEmpresarial= random.nextInt(11);
        this.emprendimiento= random.nextInt(11);
        this.voluntariado= random.nextInt(11);
    }
    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getInvestigacion() {
        return investigacion;
    }

    public void setInvestigacion(int investigacion) {
        this.investigacion = investigacion;
    }

    public int getTrabajoEmpresarial() {
        return trabajoEmpresarial;
    }

    public void setTrabajoEmpresarial(int trabajoEmpresarial) {
        this.trabajoEmpresarial = trabajoEmpresarial;
    }

    public int getEmprendimiento() {
        return emprendimiento;
    }

    public void setEmprendimiento(int emprendimiento) {
        this.emprendimiento = emprendimiento;
    }

    public int getVoluntariado() {
        return voluntariado;
    }

    public void setVoluntariado(int voluntariado) {
        this.voluntariado = voluntariado;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    
}
