package com.killa.sierravp.repository;

import com.killa.sierravp.domain.Nota;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class NotaRepository {

    EntityManager entityManager;

    public NotaRepository() {
        entityManager = Persistence.createEntityManagerFactory("UnidadPersistencia").createEntityManager();
    }
    public List<Nota> obtenerNotasPorCodigoAlumno(int codigoAlumno) {
        try {
            return entityManager.createQuery("SELECT a FROM Alumno a JOIN a.notas n WHERE n.clase.id = :codigo_alumno", Nota.class)
                    .setParameter("codigo_alumno", codigoAlumno)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }
}