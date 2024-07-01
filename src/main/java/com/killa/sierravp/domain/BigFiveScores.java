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
public class BigFiveScores {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "codigo_alumno", referencedColumnName = "codigo")
    private Alumno alumno;
    // Neuroticism OK OK
    private int ansiedad;
    private int enojo; //enojo es mejor porque ira despierta suceptibilidades xd
    private int depresion;
    private int verguenza;
    private int faltaDeAutocontrol;//se puede traducir como F de moderacion tmb
    private int vulnerabilidad;

    // Extroversion OK OK
    private int amabilidad; // cordialidad
    private int sociabilidad; //se puede traducir a gregarismo
    private int asertividad; //en la pag le dicen confianza
    private int nivelDeActividad;
    private int busquedaDeNuevasExperiencias;
    private int alegria;

    // Apertura a experiencias OK OK
    private int imaginacion;
    private int interesArtistico;
    private int sensibilidad; //auto conciencia emocional
    private int ansiasDeAventura;
    private int intelecto;
    private int liberalismo;

    // Simpatia/Amabilidad OK OK
    private int confianzaEnOtros;
    private int moralidad;
    private int altruismo;
    private int cooperacion;
    private int modestia;
    private int empatia;//tambien se puede traducir como compasion

    // Escrupulosidad OK OK
    private int autoEficacia;
    private int orden;
    private int sentidoDelDeber;
    private int disciplina;
    private int prudencia;
    private int orientacionAObjetivos;//logro de metas

    public BigFiveScores() {
        Random random = new Random();

        // Asignación de valores aleatorios para Neuroticism
        this.ansiedad = random.nextInt(21); // valores de 0 a 20
        this.enojo = random.nextInt(21);
        this.depresion = random.nextInt(21);
        this.verguenza = random.nextInt(21);
        this.faltaDeAutocontrol = random.nextInt(21);
        this.vulnerabilidad = random.nextInt(21);

        // Asignación de valores aleatorios para Extroversion
        this.amabilidad = random.nextInt(21);
        this.sociabilidad = random.nextInt(21);
        this.asertividad = random.nextInt(21);
        this.nivelDeActividad = random.nextInt(21);
        this.busquedaDeNuevasExperiencias = random.nextInt(21);
        this.alegria = random.nextInt(21);

        // Asignación de valores aleatorios para Apertura a experiencias
        this.imaginacion = random.nextInt(21);
        this.interesArtistico = random.nextInt(21);
        this.sensibilidad = random.nextInt(21);
        this.ansiasDeAventura = random.nextInt(21);
        this.intelecto = random.nextInt(21);
        this.liberalismo = random.nextInt(21);

        // Asignación de valores aleatorios para Simpatia/Amabilidad
        this.confianzaEnOtros = random.nextInt(21);
        this.moralidad = random.nextInt(21);
        this.altruismo = random.nextInt(21);
        this.cooperacion = random.nextInt(21);
        this.modestia = random.nextInt(21);
        this.empatia = random.nextInt(21);

        // Asignación de valores aleatorios para Escrupulosidad
        this.autoEficacia = random.nextInt(21);
        this.orden = random.nextInt(21);
        this.sentidoDelDeber = random.nextInt(21);
        this.disciplina = random.nextInt(21);
        this.prudencia = random.nextInt(21);
        this.orientacionAObjetivos = random.nextInt(21);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnsiedad() {
        return ansiedad;
    }

    public void setAnsiedad(int ansiedad) {
        this.ansiedad = ansiedad;
    }

    public int getEnojo() {
        return enojo;
    }

    public void setEnojo(int enojo) {
        this.enojo = enojo;
    }

    public int getDepresion() {
        return depresion;
    }

    public void setDepresion(int depresion) {
        this.depresion = depresion;
    }

    public int getVerguenza() {
        return verguenza;
    }

    public void setVerguenza(int verguenza) {
        this.verguenza = verguenza;
    }

    public int getFaltaDeAutocontrol() {
        return faltaDeAutocontrol;
    }

    public void setFaltaDeAutocontrol(int faltaDeAutocontrol) {
        this.faltaDeAutocontrol = faltaDeAutocontrol;
    }

    public int getVulnerabilidad() {
        return vulnerabilidad;
    }

    public void setVulnerabilidad(int vulnerabilidad) {
        this.vulnerabilidad = vulnerabilidad;
    }

    public int getAmabilidad() {
        return amabilidad;
    }

    public void setAmabilidad(int amabilidad) {
        this.amabilidad = amabilidad;
    }

    public int getSociabilidad() {
        return sociabilidad;
    }

    public void setSociabilidad(int sociabilidad) {
        this.sociabilidad = sociabilidad;
    }

    public int getAsertividad() {
        return asertividad;
    }

    public void setAsertividad(int asertividad) {
        this.asertividad = asertividad;
    }

    public int getNivelDeActividad() {
        return nivelDeActividad;
    }

    public void setNivelDeActividad(int nivelDeActividad) {
        this.nivelDeActividad = nivelDeActividad;
    }

    public int getBusquedaDeNuevasExperiencias() {
        return busquedaDeNuevasExperiencias;
    }

    public void setBusquedaDeNuevasExperiencias(int busquedaDeNuevasExperiencias) {
        this.busquedaDeNuevasExperiencias = busquedaDeNuevasExperiencias;
    }

    public int getAlegria() {
        return alegria;
    }

    public void setAlegria(int alegria) {
        this.alegria = alegria;
    }

    public int getImaginacion() {
        return imaginacion;
    }

    public void setImaginacion(int imaginacion) {
        this.imaginacion = imaginacion;
    }

    public int getInteresArtistico() {
        return interesArtistico;
    }

    public void setInteresArtistico(int interesArtistico) {
        this.interesArtistico = interesArtistico;
    }

    public int getSensibilidad() {
        return sensibilidad;
    }

    public void setSensibilidad(int sensibilidad) {
        this.sensibilidad = sensibilidad;
    }

    public int getAnsiasDeAventura() {
        return ansiasDeAventura;
    }

    public void setAnsiasDeAventura(int ansiasDeAventura) {
        this.ansiasDeAventura = ansiasDeAventura;
    }

    public int getIntelecto() {
        return intelecto;
    }

    public void setIntelecto(int intelecto) {
        this.intelecto = intelecto;
    }

    public int getLiberalismo() {
        return liberalismo;
    }

    public void setLiberalismo(int liberalismo) {
        this.liberalismo = liberalismo;
    }

    public int getConfianzaEnOtros() {
        return confianzaEnOtros;
    }

    public void setConfianzaEnOtros(int confianzaEnOtros) {
        this.confianzaEnOtros = confianzaEnOtros;
    }

    public int getMoralidad() {
        return moralidad;
    }

    public void setMoralidad(int moralidad) {
        this.moralidad = moralidad;
    }

    public int getAltruismo() {
        return altruismo;
    }

    public void setAltruismo(int altruismo) {
        this.altruismo = altruismo;
    }

    public int getCooperacion() {
        return cooperacion;
    }

    public void setCooperacion(int cooperacion) {
        this.cooperacion = cooperacion;
    }

    public int getModestia() {
        return modestia;
    }

    public void setModestia(int modestia) {
        this.modestia = modestia;
    }

    public int getEmpatia() {
        return empatia;
    }

    public void setEmpatia(int empatia) {
        this.empatia = empatia;
    }

    public int getAutoEficacia() {
        return autoEficacia;
    }

    public void setAutoEficacia(int autoEficacia) {
        this.autoEficacia = autoEficacia;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getSentidoDelDeber() {
        return sentidoDelDeber;
    }

    public void setSentidoDelDeber(int sentidoDelDeber) {
        this.sentidoDelDeber = sentidoDelDeber;
    }

    public int getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(int disciplina) {
        this.disciplina = disciplina;
    }

    public int getPrudencia() {
        return prudencia;
    }

    public void setPrudencia(int prudencia) {
        this.prudencia = prudencia;
    }

    public int getOrientacionAObjetivos() {
        return orientacionAObjetivos;
    }

    public void setOrientacionAObjetivos(int orientacionAObjetivos) {
        this.orientacionAObjetivos = orientacionAObjetivos;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

}
