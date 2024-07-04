/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Profesor;
import com.killa.sierravp.domain.Usuario;
import com.killa.sierravp.repository.Universidad;

import java.util.HashMap;
import java.util.Map;

public class Login {

    private Universidad universidad;
    private Map<Integer, Usuario> usuariosRegistrados;

    public Login(Universidad universidad) {
        this.universidad = universidad;
        this.usuariosRegistrados = new HashMap<>();
        registrarUsuarios();
    }

    // Método para registrar todos los usuarios en el HashMap para acceso rápido
    private void registrarUsuarios() {
        for (String nombreFacultad : universidad.getFacultades().keySet()) {
            for (Usuario usuario : universidad.obtenerUsuariosPorFacultad(nombreFacultad)) {
                usuariosRegistrados.put(usuario.getCodigo(), usuario);
            }
        }
    }

    // Método para verificar el login y devolver el tipo de usuario (alumno o profesor)
    public String verificarLogin(int codigoUsuario, String correo, String contraseña) {
        Usuario usuario = usuariosRegistrados.get(codigoUsuario);
        if (usuario == null || !usuario.getCorreo().equals(correo) || !usuario.getContraseña().equals(contraseña)) {
            return "no encontrado";
        }
        if (usuario instanceof Alumno) {
            return "alumno";
        } else if (usuario instanceof Profesor) {
            return "profesor";
        } else {
            return "no encontrado";
        }
    }

    int obtenerCodigoUsuarioPorCorreo(String correo, String contrasena) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
