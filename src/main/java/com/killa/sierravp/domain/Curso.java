/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;

/**
 *
 * @author karlo
 */
@Entity
public class Curso implements Serializable{
    @Id
    int id;
    String nombre;
    
}
