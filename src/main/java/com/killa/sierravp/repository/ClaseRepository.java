package com.killa.sierravp.repository;

import com.killa.sierravp.domain.Clase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ClaseRepository {

    private EntityManager entityManager;

    public ClaseRepository() {
        entityManager = Persistence.createEntityManagerFactory("UnidadPersistencia").createEntityManager();
    }

    public Clase findById(int id) {
        return entityManager.find(Clase.class, id);
    }

    public List<Clase> findByAlumnoId(int codigoAlumno) {
        TypedQuery<Clase> query = entityManager.createQuery(
                "SELECT c FROM Clase c JOIN c.alumnos a WHERE a.codigo = :codigoAlumno", Clase.class);
        query.setParameter("codigoAlumno", codigoAlumno);
        return query.getResultList();
    }

    public List<Clase> findByProfesorId(int codigoProfesor) {
        TypedQuery<Clase> query = entityManager.createQuery(
                "SELECT c FROM Clase c WHERE c.profesor.dni = :codigoProfesor", Clase.class);
        query.setParameter("codigoProfesor", codigoProfesor);
        return query.getResultList();
    }

    public List<Clase> findAll() {
        TypedQuery<Clase> query = entityManager.createQuery("SELECT c FROM Clase c", Clase.class);
        return query.getResultList();
    }

    public void save(Clase clase) {
        entityManager.getTransaction().begin();
        entityManager.persist(clase);
        entityManager.getTransaction().commit();
    }

    public void update(Clase clase) {
        entityManager.getTransaction().begin();
        entityManager.merge(clase);
        entityManager.getTransaction().commit();
    }

    public void delete(Clase clase) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(clase) ? clase : entityManager.merge(clase));
        entityManager.getTransaction().commit();
    }
}
