package com.killa.sierravp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author karlo
 */
@Entity
public class Profesor extends Usuario {
    @OneToMany(mappedBy = "profesor")
    private Set<Clase> clases;

    @ManyToMany
    @JoinTable(
        name = "profesores_ep",
        joinColumns = @JoinColumn(name = "profesor_id", referencedColumnName = "DNI"),
        inverseJoinColumns = @JoinColumn(name = "ep_id", referencedColumnName = "id"))
    private Set<EscuelaProfesional> eps;

    public Profesor() {
    }

    public Set<Clase> getClases() {
        return clases;
    }

    public void setClases(Set<Clase> clases) {
        this.clases = clases;
    }

    public Set<EscuelaProfesional> getEps() {
        return eps;
    }

    public void setEps(Set<EscuelaProfesional> eps) {
        this.eps = eps;
    }
}
