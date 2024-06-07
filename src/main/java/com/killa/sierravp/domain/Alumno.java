/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 *
 * @author karlo
 */
@Entity
@DiscriminatorValue("ALUMNO")
public class Alumno extends Usuario{
    int codigo;
    int facultadId;
    boolean enRepitencia;
    
}
