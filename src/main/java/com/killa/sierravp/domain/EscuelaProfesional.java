/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.domain;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author karlo
 */
public class EscuelaProfesional {
    int id;
    @ManyToOne
    @JoinColumn(name="facultad_id")
    Facultad facultad;
    String nombre;
}
