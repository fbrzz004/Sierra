/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.Set;

/**
 *
 * @author karlo
 */
//en un muchos a muchos es ventajoso usar Set porque asi no realizo operaciones extra en bdd
//se borra de forma directa solo el elemento que quiero borrar
@Entity
public class Clase {
    @Id
    int id;
    @ManyToMany(mappedBy = "clases")
    Set<Alumno> alumnos;
    @ManyToOne
            @JoinColumn(name = "profesor_id") 
    Profesor profesor;
    @ManyToOne
    Set<Curso> curso;
}
