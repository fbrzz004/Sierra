/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.service;

import com.killa.sierravp.domain.BigFiveScores;
import com.killa.sierravp.repository.BigFiveScoresRepository;

/**
 *
 * @author karlo
 */
public class BigFiveScoresService {
    BigFiveScoresRepository bfsr= new BigFiveScoresRepository();
    
    public BigFiveScores BuscarByCod(int cod){
        return bfsr.obtenerByCodigo(cod);
    }
    
    public float similitudCoseno(int idAlumno1, int idAlumno2){
        BigFiveScores alumno1Scores = BuscarByCod(idAlumno1);
        BigFiveScores alumno2Scores = BuscarByCod(idAlumno2);

        // Calcular el producto punto
        float productoPunto = (alumno1Scores.getAnsiedad() * alumno2Scores.getAnsiedad()) +
                              (alumno1Scores.getEnojo() * alumno2Scores.getEnojo()) +
                              (alumno1Scores.getDepresion() * alumno2Scores.getDepresion()) +
                              (alumno1Scores.getVerguenza() * alumno2Scores.getVerguenza()) +
                              (alumno1Scores.getFaltaDeAutocontrol() * alumno2Scores.getFaltaDeAutocontrol()) +
                              (alumno1Scores.getVulnerabilidad() * alumno2Scores.getVulnerabilidad()) +
                              (alumno1Scores.getAmabilidad() * alumno2Scores.getAmabilidad()) +
                              (alumno1Scores.getSociabilidad() * alumno2Scores.getSociabilidad()) +
                              (alumno1Scores.getAsertividad() * alumno2Scores.getAsertividad()) +
                              (alumno1Scores.getNivelDeActividad() * alumno2Scores.getNivelDeActividad()) +
                              (alumno1Scores.getBusquedaDeNuevasExperiencias() * alumno2Scores.getBusquedaDeNuevasExperiencias()) +
                              (alumno1Scores.getAlegria() * alumno2Scores.getAlegria()) +
                              (alumno1Scores.getImaginacion() * alumno2Scores.getImaginacion()) +
                              (alumno1Scores.getInteresArtistico() * alumno2Scores.getInteresArtistico()) +
                              (alumno1Scores.getSensibilidad() * alumno2Scores.getSensibilidad()) +
                              (alumno1Scores.getAnsiasDeAventura() * alumno2Scores.getAnsiasDeAventura()) +
                              (alumno1Scores.getIntelecto() * alumno2Scores.getIntelecto()) +
                              (alumno1Scores.getLiberalismo() * alumno2Scores.getLiberalismo()) +
                              (alumno1Scores.getConfianzaEnOtros() * alumno2Scores.getConfianzaEnOtros()) +
                              (alumno1Scores.getMoralidad() * alumno2Scores.getMoralidad()) +
                              (alumno1Scores.getAltruismo() * alumno2Scores.getAltruismo()) +
                              (alumno1Scores.getCooperacion() * alumno2Scores.getCooperacion()) +
                              (alumno1Scores.getModestia() * alumno2Scores.getModestia()) +
                              (alumno1Scores.getEmpatia() * alumno2Scores.getEmpatia()) +
                              (alumno1Scores.getAutoEficacia() * alumno2Scores.getAutoEficacia()) +
                              (alumno1Scores.getOrden() * alumno2Scores.getOrden()) +
                              (alumno1Scores.getSentidoDelDeber() * alumno2Scores.getSentidoDelDeber()) +
                              (alumno1Scores.getDisciplina() * alumno2Scores.getDisciplina()) +
                              (alumno1Scores.getPrudencia() * alumno2Scores.getPrudencia()) +
                              (alumno1Scores.getOrientacionAObjetivos() * alumno2Scores.getOrientacionAObjetivos());

        // Calcular las normas
        float normaAlumno1 = (float) Math.sqrt(
                Math.pow(alumno1Scores.getAnsiedad(), 2) +
                Math.pow(alumno1Scores.getEnojo(), 2) +
                Math.pow(alumno1Scores.getDepresion(), 2) +
                Math.pow(alumno1Scores.getVerguenza(), 2) +
                Math.pow(alumno1Scores.getFaltaDeAutocontrol(), 2) +
                Math.pow(alumno1Scores.getVulnerabilidad(), 2) +
                Math.pow(alumno1Scores.getAmabilidad(), 2) +
                Math.pow(alumno1Scores.getSociabilidad(), 2) +
                Math.pow(alumno1Scores.getAsertividad(), 2) +
                Math.pow(alumno1Scores.getNivelDeActividad(), 2) +
                Math.pow(alumno1Scores.getBusquedaDeNuevasExperiencias(), 2) +
                Math.pow(alumno1Scores.getAlegria(), 2) +
                Math.pow(alumno1Scores.getImaginacion(), 2) +
                Math.pow(alumno1Scores.getInteresArtistico(), 2) +
                Math.pow(alumno1Scores.getSensibilidad(), 2) +
                Math.pow(alumno1Scores.getAnsiasDeAventura(), 2) +
                Math.pow(alumno1Scores.getIntelecto(), 2) +
                Math.pow(alumno1Scores.getLiberalismo(), 2) +
                Math.pow(alumno1Scores.getConfianzaEnOtros(), 2) +
                Math.pow(alumno1Scores.getMoralidad(), 2) +
                Math.pow(alumno1Scores.getAltruismo(), 2) +
                Math.pow(alumno1Scores.getCooperacion(), 2) +
                Math.pow(alumno1Scores.getModestia(), 2) +
                Math.pow(alumno1Scores.getEmpatia(), 2) +
                Math.pow(alumno1Scores.getAutoEficacia(), 2) +
                Math.pow(alumno1Scores.getOrden(), 2) +
                Math.pow(alumno1Scores.getSentidoDelDeber(), 2) +
                Math.pow(alumno1Scores.getDisciplina(), 2) +
                Math.pow(alumno1Scores.getPrudencia(), 2) +
                Math.pow(alumno1Scores.getOrientacionAObjetivos(), 2)
        );

        float normaAlumno2 = (float) Math.sqrt(
                Math.pow(alumno2Scores.getAnsiedad(), 2) +
                Math.pow(alumno2Scores.getEnojo(), 2) +
                Math.pow(alumno2Scores.getDepresion(), 2) +
                Math.pow(alumno2Scores.getVerguenza(), 2) +
                Math.pow(alumno2Scores.getFaltaDeAutocontrol(), 2) +
                Math.pow(alumno2Scores.getVulnerabilidad(), 2) +
                Math.pow(alumno2Scores.getAmabilidad(), 2) +
                Math.pow(alumno2Scores.getSociabilidad(), 2) +
                Math.pow(alumno2Scores.getAsertividad(), 2) +
                Math.pow(alumno2Scores.getNivelDeActividad(), 2) +
                Math.pow(alumno2Scores.getBusquedaDeNuevasExperiencias(), 2) +
                Math.pow(alumno2Scores.getAlegria(), 2) +
                Math.pow(alumno2Scores.getImaginacion(), 2) +
                Math.pow(alumno2Scores.getInteresArtistico(), 2) +
                Math.pow(alumno2Scores.getSensibilidad(), 2) +
                Math.pow(alumno2Scores.getAnsiasDeAventura(), 2) +
                Math.pow(alumno2Scores.getIntelecto(), 2) +
                Math.pow(alumno2Scores.getLiberalismo(), 2) +
                Math.pow(alumno2Scores.getConfianzaEnOtros(), 2) +
                Math.pow(alumno2Scores.getMoralidad(), 2) +
                Math.pow(alumno2Scores.getAltruismo(), 2) +
                Math.pow(alumno2Scores.getCooperacion(), 2) +
                Math.pow(alumno2Scores.getModestia(), 2) +
                Math.pow(alumno2Scores.getEmpatia(), 2) +
                Math.pow(alumno2Scores.getAutoEficacia(), 2) +
                Math.pow(alumno2Scores.getOrden(), 2) +
                Math.pow(alumno2Scores.getSentidoDelDeber(), 2) +
                Math.pow(alumno2Scores.getDisciplina(), 2) +
                Math.pow(alumno2Scores.getPrudencia(), 2) +
                Math.pow(alumno2Scores.getOrientacionAObjetivos(), 2)
        );

        // Calcular la similitud coseno
        return productoPunto / (normaAlumno1 * normaAlumno2);
    }
    
}
