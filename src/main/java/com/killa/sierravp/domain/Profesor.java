package com.killa.sierravp.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Column;
import jakarta.persistence.PreUpdate;
import java.util.Set;

/**
 *
 * @author karlo
 */
@Entity
@DiscriminatorValue("profesor")
public class Profesor extends Usuario implements UsuarioGenerico{

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Clase> clases;

    @ManyToMany
    @JoinTable(
        name = "profesores_ep",
        joinColumns = @JoinColumn(name = "profesor_id", referencedColumnName = "DNI"),
        inverseJoinColumns = @JoinColumn(name = "ep_id", referencedColumnName = "id"))
    private Set<EscuelaProfesional> eps;
        
    public Profesor() {
        super(); //garantizo que se ejecuta el const del padre
    }

    @PrePersist
    @PreUpdate
    private void ensureEpNotNull() {
        if (eps == null || eps.isEmpty()) {
            throw new IllegalStateException("eps must not be null or empty, se ejecuto el prePersist");
        }
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
