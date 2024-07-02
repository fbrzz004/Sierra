package com.killa.sierravp.service;

import com.killa.sierravp.domain.Usuario;
import com.killa.sierravp.repository.UsuarioRepository;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author karlo
 */
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService() {
        usuarioRepository = new UsuarioRepository();
    }

    public boolean autenticarUsuario(String correo, String contraseña) {
        Usuario usuario = usuarioRepository.findByCorreoAndContraseña(correo, contraseña);
        return usuario != null;
    }

    public boolean modificarPerfil(int dni, String primerNombre, String segundoNombre, String primerApe, String segundoApe,String contraseña, String correo) {
	Usuario usuario = usuarioRepository.findById(dni);
	if (usuario != null) {
	    usuario.actualizarPerfil(primerNombre, segundoNombre, primerApe, segundoApe, contraseña, correo);
	    usuarioRepository.update(usuario);
	    return true;
	}
	return false;
    }
}
