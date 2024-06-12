/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.service;

import com.killa.sierravp.domain.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;

public class AutenticacionSistema {

    private static final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml") // Archivo de configuración de Hibernate
            .addAnnotatedClass(Usuario.class) // Clase de entidad
            .buildSessionFactory();

    public boolean validarCredenciales(String usuario, String contraseña) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

             // Buscar el usuario en la base de datos
             Usuario usuarioBD = session.createQuery("from Usuario u where u.correo = :usuario", Usuario.class)
                    .setParameter("usuario", usuario)
                    .uniqueResult();

             session.getTransaction().commit();

            if (usuarioBD != null) {
                // Validar las credenciales directamente con los campos del usuario recuperado
                return usuarioBD.getCorreo().equals(usuario) && usuarioBD.getContraseña().equals(contraseña);
            } else {
                return false;
            }
        }
    } 
}