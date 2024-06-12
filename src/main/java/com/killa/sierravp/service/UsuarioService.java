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
}