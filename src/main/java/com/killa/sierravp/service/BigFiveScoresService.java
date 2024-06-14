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
}
