/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 *
 * @author karlo
 */
@Entity
public class Curso implements Serializable{
    @Id
    int id;
    String nombre;
    @OneToMany(mappedBy = "curso")
    private Set<Clase> clases;
}
