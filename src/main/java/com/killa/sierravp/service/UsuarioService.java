package com.killa.sierravp.service;

import com.killa.sierravp.domain.Ranking;
import com.killa.sierravp.domain.Usuario;
import com.killa.sierravp.repository.UsuarioRepository;
import com.killa.sierravp.repository.Universidad;
import java.util.List;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private RankingService rankingService;

    public UsuarioService(Universidad universidad) {
        usuarioRepository = new UsuarioRepository();
        rankingService = new RankingService(universidad);
    }

    public boolean autenticarUsuario(String correo, String contraseña) {
        Usuario usuario = usuarioRepository.findByCorreoAndContraseña(correo, contraseña);
        return usuario != null;
    }

    public boolean modificarPerfil(int dni, String primerNombre, String segundoNombre, String primerApe, String segundoApe, String contraseña, String correo) {
        Usuario usuario = usuarioRepository.findById(dni);
        if (usuario != null) {
            usuario.setPrimerNombre(primerNombre);
            usuario.setSegundoNombre(segundoNombre);
            usuario.setPrimerApellido(primerApe);
            usuario.setSegundoApellido(segundoApe);
            usuario.setContraseña(contraseña);
            usuario.setCorreo(correo);
            usuarioRepository.update(usuario);
            return true;
        }
        return false;
    }

    // Método para obtener y ordenar rankings
    public List<Ranking> obtenerYOrdenarRankings() {
        rankingService.generarRanking(); // Generar el ranking si no existe
        List<Ranking> rankings = rankingService.obtenerRankings();
        rankingService.ordenarRankings(rankings);
        return rankings;
    }

    // Método para imprimir rankings
    public void imprimirRankings(List<Ranking> rankings) {
        if (rankings.isEmpty()) {
            System.out.println("No hay rankings disponibles para mostrar.");
            return;
        }
        
        for (Ranking ranking : rankings) {
            System.out.println("Posición: " + ranking.getPosicion() +
                               ", Alumno: " + ranking.getAlumno().getPrimerNombre() + " " + ranking.getAlumno().getPrimerApellido() +
                               ", Escuela: " + ranking.getEscuela().getNombre());
        }
    }
}
