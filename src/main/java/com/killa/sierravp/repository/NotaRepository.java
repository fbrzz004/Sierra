package com.killa.sierravp.repository;

import com.killa.sierravp.domain.Nota;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class NotaRepository {

    EntityManager entityManager;

    public NotaRepository() {
        entityManager = Persistence.createEntityManagerFactory("UnidadPersistencia").createEntityManager();
    }

    public List<Nota> findByClaseId(int claseId) {
        TypedQuery<Nota> query = entityManager.createQuery(
                "SELECT n FROM Nota n WHERE n.clase.id = :claseId", Nota.class);
        query.setParameter("claseId", claseId);
        return query.getResultList();
    }

    public List<Nota> obtenerNotasPorAlumnoYCurso(int codigoAlumno, int idCurso) {
        try {
            return entityManager.createQuery(
                    "SELECT n FROM Nota n WHERE n.alumno.codigo = :codigoAlumno AND n.curso.id = :idCurso", Nota.class)
                    .setParameter("codigoAlumno", codigoAlumno)
                    .setParameter("idCurso", idCurso)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }
}
