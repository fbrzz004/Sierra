/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.util;

/**
 *
 * @author karlo
    }
 */
public class Caracteristica_y_Id {

    private Caractistica caractistica;
    private AtributoBf5 atributoBf5;
    private AtributosInteresesAcade atributoIA;
    //constructor cuando la dupla es Bf5

    public Caracteristica_y_Id(Caractistica caractistica, AtributoBf5 atributoBf5) {
        this.caractistica = caractistica;
        this.atributoBf5 = atributoBf5;
    }

    public Caracteristica_y_Id(Caractistica caractistica, AtributosInteresesAcade atributoIA) {
        this.caractistica = caractistica;
        this.atributoIA = atributoIA;
    }

    public Caractistica getCaractistica() {
        return caractistica;
    }

    public void setCaractistica(Caractistica caractistica) {
        this.caractistica = caractistica;
    }   

    public AtributoBf5 getAtributoBf5() {
        return atributoBf5;
    }

    public void setAtributoBf5(AtributoBf5 atributoBf5) {
        this.atributoBf5 = atributoBf5;
    }

    public AtributosInteresesAcade getAtributoIA() {
        return atributoIA;
    }

    public void setAtributoIA(AtributosInteresesAcade atributoIA) {
        this.atributoIA = atributoIA;
    }
    
    
}
