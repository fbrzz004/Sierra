
package com.killa.sierravp.repository;

import com.killa.sierravp.domain.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class UsuarioRepository {
    
    private EntityManager entityManager;

    public UsuarioRepository() {
        entityManager = Persistence.createEntityManagerFactory("UnidadPersistencia").createEntityManager();
    }

    public Usuario findByCorreoAndContraseña(String correo, String contraseña) {
        try {
            TypedQuery<Usuario> query = entityManager.createQuery(
                "SELECT u FROM Usuario u WHERE u.correo = :correo AND u.contraseña = :contraseña", Usuario.class);
            query.setParameter("correo", correo);
            query.setParameter("contraseña", contraseña);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Usuario findById(int dni) {
        return entityManager.find(Usuario.class, dni);
    }

    public void update(Usuario usuario) {
        entityManager.getTransaction().begin();
        entityManager.merge(usuario);
        entityManager.getTransaction().commit();
    }
}
