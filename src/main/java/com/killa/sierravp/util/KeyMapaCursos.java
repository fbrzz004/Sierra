/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.util;

import java.util.Objects;

/**
 *
 * @author karlo
 */
public class KeyMapaCursos {
    String Facultad;
    int ciclo;
    int numeroCurso;

    public KeyMapaCursos(String Facultad, int ciclo, int numeroCurso) {
        this.Facultad = Facultad;
        this.ciclo = ciclo;
        this.numeroCurso = numeroCurso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.Facultad);
        hash = 47 * hash + this.ciclo;
        hash = 47 * hash + this.numeroCurso;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KeyMapaCursos other = (KeyMapaCursos) obj;
        if (this.ciclo != other.ciclo) {
            return false;
        }
        if (this.numeroCurso != other.numeroCurso) {
            return false;
        }
        return Objects.equals(this.Facultad, other.Facultad);
    }

    @Override
    public String toString() {
        return "KeyMapaCursos{" + "Facultad=" + Facultad + ", ciclo=" + ciclo + ", numeroCurso=" + numeroCurso + '}';
    }
       
}
