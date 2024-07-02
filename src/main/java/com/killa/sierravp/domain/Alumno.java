package com.killa.sierravp.domain;

import static com.killa.sierravp.util.AtributosInteresesAcade.investigacion;
import com.killa.sierravp.util.Caracteristica_y_Id;
import com.killa.sierravp.util.Caractistica;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.Set;

/**
 *
 * @author karlo
 */
@Entity
@DiscriminatorValue("alumno")
public class Alumno extends Usuario implements UsuarioGenerico{

    @ManyToOne
    @JoinColumn(name = "facultad_id", referencedColumnName = "id", nullable = false)
    private Facultad facultad;

    @ManyToOne
    @JoinColumn(name = "ep_id", referencedColumnName = "id", nullable = false)
    private EscuelaProfesional ep;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "alumnos_clases",
            joinColumns = @JoinColumn(name = "codigo_alumno", referencedColumnName = "codigo"),
            inverseJoinColumns = @JoinColumn(name = "curso_id", referencedColumnName = "id"))
    private Set<Clase> clases;

    @OneToOne(mappedBy = "alumno", cascade = CascadeType.ALL, orphanRemoval = true)
    private Ranking ranking;

    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Nota> notas;

    private boolean regular;
    
    private byte ciclo;

    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CRA> craHistorico;

    // Nuevos atributos para manejo del rendimiento académico
    @Column(name = "cra_ponderado_actual")
    private double craPonderadoActual;

    @ElementCollection
    @CollectionTable(name = "cra_historico_values", joinColumns = @JoinColumn(name = "alumno_id"))
    @Column(name = "cra_value")
    private Set<Double> craHistoricoValues;

    @Column(name = "posicion_ranking")
    private int posicionRanking;

    @OneToOne(mappedBy = "alumno", cascade = CascadeType.ALL, orphanRemoval = true)
    private BigFiveScores bfScores;
    @OneToOne(mappedBy = "alumno", cascade = CascadeType.ALL, orphanRemoval = true)
    private InteresesAcademicos interesesAcademicos;

    // Constructor por defecto necesario para JPA
    public Alumno() {
        super(); 
        this.regular = true;
    }
      
    //Metodos especiales de alumno (se enucentra en mi clase alumno)
    public int procesarCaracte_Id(Caracteristica_y_Id ci) {
        int valor;
        if (ci.getCaractistica() == Caractistica.BigFiveScores) {
            var a = ci.getAtributoBf5();
            valor=switch (a) {
                case ansiedad -> this.bfScores.getAnsiedad();
            case enojo -> this.bfScores.getEnojo();
            case depresion -> this.bfScores.getDepresion();
            case verguenza -> this.bfScores.getVerguenza();
            case faltaDeAutocontrol -> this.bfScores.getFaltaDeAutocontrol();
            case vulnerabilidad -> this.bfScores.getVulnerabilidad();
            case amabilidad -> this.bfScores.getAmabilidad();
            case sociabilidad -> this.bfScores.getSociabilidad();
            case asertividad -> this.bfScores.getAsertividad();
            case nivelDeActividad -> this.bfScores.getNivelDeActividad();
            case busquedaDeNuevasExperiencias -> this.bfScores.getBusquedaDeNuevasExperiencias();
            case alegria -> this.bfScores.getAlegria();
            case imaginacion -> this.bfScores.getImaginacion();
            case interesArtistico -> this.bfScores.getInteresArtistico();
            case sensibilidad -> this.bfScores.getSensibilidad();
            case ansiasDeAventura -> this.bfScores.getAnsiasDeAventura();
            case intelecto -> this.bfScores.getIntelecto();
            case liberalismo -> this.bfScores.getLiberalismo();
            case confianzaEnOtros -> this.bfScores.getConfianzaEnOtros();
            case moralidad -> this.bfScores.getMoralidad();
            case altruismo -> this.bfScores.getAltruismo();
            case cooperacion -> this.bfScores.getCooperacion();
            case modestia -> this.bfScores.getModestia();
            case empatia -> this.bfScores.getEmpatia();
            case autoEficacia -> this.bfScores.getAutoEficacia();
            case orden -> this.bfScores.getOrden();
            case sentidoDelDeber -> this.bfScores.getSentidoDelDeber();
            case disciplina -> this.bfScores.getDisciplina();
            case prudencia -> this.bfScores.getPrudencia();
            case orientacionAObjetivos -> this.bfScores.getOrientacionAObjetivos();
            };
        } else {
            var a = ci.getAtributoIA();
            valor= switch (a) {
                case investigacion -> this.interesesAcademicos.getInvestigacion();
                case trabajoEmpresarial -> this.interesesAcademicos.getTrabajoEmpresarial();
                case emprendimiento -> this.interesesAcademicos.getEmprendimiento();
                case voluntariado -> this.interesesAcademicos.getVoluntariado();
            };
        }
        return valor;
    }

    // Getters y setters para los nuevos atributos
    public double getCraPonderadoActual() {
        return craPonderadoActual;
    }

    public void setCraPonderadoActual(double craPonderadoActual) {
        this.craPonderadoActual = craPonderadoActual;
    }

    public Set<Double> getCraHistoricoValues() {
        return craHistoricoValues;
    }

    public void setCraHistoricoValues(Set<Double> craHistoricoValues) {
        this.craHistoricoValues = craHistoricoValues;
    }

    public int getPosicionRanking() {
        return posicionRanking;
    }

    public void setPosicionRanking(int posicionRanking) {
        this.posicionRanking = posicionRanking;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public EscuelaProfesional getEp() {
        return ep;
    }

    public void setEp(EscuelaProfesional ep) {
        this.ep = ep;
    }

    public Set<Clase> getClases() {
        return clases;
    }

    public void setClases(Set<Clase> clases) {
        this.clases = clases;
    }

    public boolean isRegular() {
        return regular;
    }

    public void setRegular(boolean regular) {
        this.regular = regular;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    public Set<Nota> getNotas() {
        return notas;
    }

    public void setNotas(Set<Nota> notas) {
        this.notas = notas;
    }

    public Set<CRA> getCraHistorico() {
        return craHistorico;
    }

    public void setCraHistorico(Set<CRA> craHistorico) {
        this.craHistorico = craHistorico;
    }

    public BigFiveScores getBfScores() {
        return bfScores;
    }

    public void setBfScores(BigFiveScores bfScores) {
        this.bfScores = bfScores;
    }

    public InteresesAcademicos getInteresesAcademicos() {
        return interesesAcademicos;
    }

    public void setInteresesAcademicos(InteresesAcademicos interesesAcademicos) {
        this.interesesAcademicos = interesesAcademicos;
    }

    public byte getCiclo() {
        return ciclo;
    }

    public void setCiclo(byte ciclo) {
        this.ciclo = ciclo;
    }

    @Override
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    @Override
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    @Override
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    @Override
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }
    
}