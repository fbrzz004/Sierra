package com.killa.sierravp.domain;

import com.killa.sierravp.util.CodigoGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author karlo
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int DNI;

    @Column(unique = true, nullable = false) //no deja crear profe ni admin si no le asigno un valor al codigo
     private int codigo;
    
    @Column(nullable = false)
     String primerNombre;

    @Column(nullable = false)
     String segundoNombre;
    
    @Column(nullable = false)
     String primerApellido;
    
    @Column(nullable = false)
     String segundoApellido;

    @Column(nullable = false)
    private String contraseña;

    @Column(nullable = false)
    private String correo;

    public boolean autenticar(String user, String password) {
        return false;
    }

    public Usuario() {
        this.contraseña=generarContra(6);
        this.codigo=  CodigoGenerator.generate();
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void generarCorreo(){
        StringBuilder sb = new StringBuilder(this.primerNombre.toLowerCase());
        sb.append(".").append(this.primerApellido.toLowerCase()).append("@unmsm.edu.pe");
        this.correo=sb.toString();
    }
    
    public static String generarContra(int tamaño) {

        int leftLimit = 48; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = tamaño;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString(); 
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public void actualizarPerfil(String primerNombre, String segundoNombre, String primerApe, String segundoApe,String contraseña, String correo) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApe;
        this.segundoApellido = segundoApe;
        this.contraseña = contraseña;
        this.correo = correo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
}
