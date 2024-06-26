package com.killa.sierravp.repository;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class ProfesorRepository {

    EntityManager entityManager;

    public ProfesorRepository() {
        entityManager = Persistence.createEntityManagerFactory("UnidadPersistencia").createEntityManager();
    }

    //El problema que tiene 
    //findClaseByID es que el profesor no conoce el id, para que cumpla su funcion deberias 
    //recuperar todas las clases que dicta y con la lista que te retorna puedes obtener el id
    //por ejemplo dicta Algo 1 y Algo 2 cuando tienes eso en la lista la app le muestra sus cursos y el selecciona un curso,
    //con eso obtienes el id del que selecciono y ahi si aplicas tus metodos de obtenerAlum
    //pues seria algo como id = clasex.getId() lo que al final le enviarias a obtenerAlumno
    public Clase findClaseByID(int id) {
        try {
            return entityManager.find(Clase.class, id);
        } finally {
            entityManager.close();
        }
    }

    public List<Alumno> obtenerAlumnosPorClase(int claseId) {
        try {
            return entityManager.createQuery(
                    "SELECT a FROM Alumno a JOIN a.clases c WHERE c.id = :claseId", Alumno.class)
                    .setParameter("claseId", claseId)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

        /*
        Forma anterior, el problema es que las notas todavia no estan en la bdd, ahora se hace buscando a los
        alumnos que llevan esa clase
        public List<Alumno> obtenerAlumnosPorClase(int claseId) {
        try {
            return entityManager.createQuery("SELECT a FROM Alumno a JOIN a.notas n WHERE n.clase.id = :claseId", Alumno.class)
                    .setParameter("claseId", claseId)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }
     */
    
    public boolean guardarNota(Nota nota) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(nota);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }
}
