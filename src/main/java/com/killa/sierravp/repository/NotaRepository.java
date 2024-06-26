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
    
    //se cambio para que te permita obtener las notas final, parcial y continua de un curso en especifico
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

    /*
    public List<Nota> obtenerNotasPorCodigoAlumno(int codigoAlumno) {
        try {
            return entityManager.createQuery("SELECT a FROM Alumno a JOIN a.notas n WHERE n.clase.id = :codigo_alumno", Nota.class)
                    .setParameter("codigo_alumno", codigoAlumno)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }
     */
    
}
