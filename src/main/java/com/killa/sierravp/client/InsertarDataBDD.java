/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.killa.sierravp.client;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.BigFiveScores;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Curso;
import com.killa.sierravp.domain.EscuelaProfesional;
import com.killa.sierravp.domain.Facultad;
import com.killa.sierravp.domain.InteresesAcademicos;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.domain.Profesor;
import com.killa.sierravp.domain.UsuarioGenerico;
import com.killa.sierravp.util.CodigoGenerator;
import com.killa.sierravp.util.MapaCursos;
import com.killa.sierravp.util.ObtenerCursos;
import com.killa.sierravp.util.TipoNota;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author karlo
 */
public class InsertarDataBDD {

    public static void main(String[] args) {
        ArrayList<String> nombresMujer = obtenerLinea("Mujeres.txt");
        ArrayList<String> nombresVaron = obtenerLinea("Hombres.txt");
        ArrayList<String> apellidos = obtenerLinea("Apellidos.txt");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadPersistencia");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        ArrayList<Clase> ordenadas = new ArrayList(251);

        Curso cursonuncaUsado = new Curso();
        cursonuncaUsado.setNombre("Sexologia De Pollos");
        Clase clasenuncaUsada = new Clase(cursonuncaUsado);
        ordenadas.add(clasenuncaUsada); //asi hago que mis indices de trabajo sean del 1 al 250 ioi
        try {
            transaction.begin();

            MapaCursos mc = ObtenerCursos.obtenerCursosDeFacultad("FDok.txt");
            EscuelaProfesional ep = em.find(EscuelaProfesional.class, 702); //derecho
            Facultad facultad = em.find(Facultad.class, 602);               //Fac derecho y ciencia politica

            if (ep == null || facultad == null) {
                throw new RuntimeException("EscuelaProfesional or Facultad not found.");
            }

            byte ciclo = 1;
            int numeroCurso = 1;

            // Mapa para almacenar las clases de cada ciclo
            Map<Byte, Set<Clase>> clasesPorCiclo = new HashMap<>();

            for (int i = 1; i <= 50; i++) {
                String nombreCurso = mc.get("Facultad de Derecho y Ciencia Política", ciclo, numeroCurso);
                Curso c = new Curso();
                c.setNombre(nombreCurso);
                Set<Clase> clasesDelCurso = new HashSet<>();
                c.setClases(clasesDelCurso);
                em.persist(c);

                for (int j = 1; j <= 5; j++) {
                    Clase clase = new Clase();
                    clase.setCurso(c);

                    // Generar y asignar profesor
                    Profesor profe = GenerarNombresUsuario(new Profesor(), nombresMujer, nombresVaron, apellidos, i * 5 + j);
                    //profe.setEps(new HashSet<>(Collections.singletonList(ep))); // Utilizamos singleton para asegurar que la colección contiene solo este EP

                    if (ep == null) {
                        throw new RuntimeException("EscuelaProfesional no encontrada.");
                    }
                    profe.setEps(Set.of(ep)); //antes usaba lo de arriba
                    profe.generarCorreo();
                    em.persist(profe);
                    //el resto permanece igual
                    clase.setProfesor(profe);
                    clasesDelCurso.add(clase);
                    ordenadas.add(clase); //del indice 1 a 5 primer curso del ciclo 6 a 10 segundo 
                    //curso de primer ciclo y asi sucesivamente 
                    em.persist(clase);

                    // Agregar la clase al mapa de clases por ciclo
                    clasesPorCiclo.computeIfAbsent(ciclo, k -> new HashSet<>()).add(clase);
                }

                numeroCurso++;
                if (numeroCurso == 6) {
                    numeroCurso = 1;
                    ciclo++;
                }
            }

            // Crear y asignar alumnos
            for (byte cicloActual = 1; cicloActual <= 10; cicloActual++) {
                for (int grupo = 1; grupo <= 5; grupo++) {
                    for (int k = 1; k <= 40; k++) {
                        Alumno alumno = GenerarNombresUsuario(new Alumno(), nombresMujer, nombresVaron, apellidos, k);
                        alumno.generarCorreo();
                        alumno.setFacultad(facultad);
                        alumno.setEp(ep);                                          
                        
                        BigFiveScores bfs = new BigFiveScores();
                        bfs.setAlumno(alumno);
                        em.persist(bfs);

                        InteresesAcademicos ia = new InteresesAcademicos();
                        ia.setAlumno(alumno);
                        em.persist(ia);

                        alumno.setCiclo(cicloActual);
                        alumno.setBfScores(bfs);
                        alumno.setInteresesAcademicos(ia);

                        // Asignar 5 clases del ciclo actual al alumno, asegurando que son de cursos diferentes
                        Set<Clase> clasesAlumno = new HashSet<>();

                        //los grupos 1 del primer ciclo son 1, 6, 11, 16 y 21
                        //los grupos 1 del segundo ciclo 26, 31, 36, 41, 46
                        //los grupos 2 del primer ciclo 2,7,12,17,22
                        //los grupos 2 del segundo ciclo 27,32,37,42,47
                        //los grupos son importantes pues nos permiten realizar la asignacion de notas
                        int indexBase = (cicloActual - 1) * 25;
                        Clase num1 = ordenadas.get(indexBase + grupo);
                        Clase num2 = ordenadas.get(indexBase + grupo + 5);
                        Clase num3 = ordenadas.get(indexBase + grupo + 10);
                        Clase num4 = ordenadas.get(indexBase + grupo + 15);
                        Clase num5 = ordenadas.get(indexBase + grupo + 20);

                        clasesAlumno.add(num1);
                        clasesAlumno.add(num2);
                        clasesAlumno.add(num3);
                        clasesAlumno.add(num4);
                        clasesAlumno.add(num5);

                        alumno.setClases(clasesAlumno);
                        em.persist(alumno);

                        // Asignar notas a cada clase del ciclo para el alumno
                        for (Clase cl : clasesAlumno) {
                            Curso curso = cl.getCurso();
                            alumno.setNotas(generarNotaAlumno(alumno, curso, cl, grupo)); //hago que se asignen sus notas para las clases 1 al 5
                        }
                    }
                }
            }

            transaction.commit();
            CodigoGenerator.saveCurrentCode(); //asi garantizo que solo se actualice el txt si se registro con exito
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static HashSet<Nota> generarNotaAlumno(Alumno alumno, Curso curso, Clase clase, int numeroClase) {
        Random random = new Random();
        HashSet<Nota> notasAlumno = new HashSet(3);
        Nota ec = new Nota(alumno, curso, clase);
        ec.setTipo(TipoNota.EC);
        Nota ep = new Nota(alumno, curso, clase);
        ep.setTipo(TipoNota.EP);
        Nota ef = new Nota(alumno, curso, clase);
        ef.setTipo(TipoNota.EF);
        if (numeroClase <= 2) { //las clases 1 y 2 tendran notas del 8 al 14
            ec.setCalificacion(random.nextInt(6) + 8);
            ep.setCalificacion(random.nextInt(6) + 8);
            ef.setCalificacion(random.nextInt(6) + 8);
        } else if (numeroClase == 3) { //la clase 3 tendran notas del 14 al 19
            ec.setCalificacion(random.nextInt(5) + 14);
            ep.setCalificacion(random.nextInt(5) + 14);
            ef.setCalificacion(random.nextInt(5) + 14);
        } else {
            ec.setCalificacion(random.nextInt(8) + 12);
            ep.setCalificacion(random.nextInt(8) + 12);
            ef.setCalificacion(random.nextInt(8) + 12);
        }
        notasAlumno.add(ec);
        notasAlumno.add(ep);
        notasAlumno.add(ef);
        return notasAlumno;
    }

    public static <T extends UsuarioGenerico> T GenerarNombresUsuario(T usuario, ArrayList<String> nombreMujer, ArrayList<String> nombreVaron, ArrayList<String> apellidos, int num) {
        Random random = new Random();
        if (num % 2 == 0) {
            int r = random.nextInt(nombreMujer.size()+1);
            String nomb1 = nombreMujer.get(r);
            usuario.setPrimerNombre(nomb1);
            int r2 = random.nextInt(nombreMujer.size()+1);
            while (nomb1.equals(nombreMujer.get(r2))) {
                r2 = random.nextInt(nombreMujer.size()+1);
            }
            usuario.setSegundoNombre(nombreMujer.get(r2));
            usuario.setPrimerApellido(apellidos.get(r));
            usuario.setSegundoApellido(apellidos.get(r2));
        } else {
            int r = random.nextInt(nombreVaron.size()+1);
            String nomb1 = nombreVaron.get(r);
            usuario.setPrimerNombre(nomb1);
            int r2 = random.nextInt(nombreVaron.size()+1);
            while (nomb1.equals(nombreMujer.get(r2))) {
                r2 = random.nextInt(nombreVaron.size()+1);
            }

            usuario.setSegundoNombre(nombreVaron.get(r2));
            usuario.setPrimerApellido(apellidos.get(r));
            usuario.setSegundoApellido(apellidos.get(random.nextInt(apellidos.size())));
        }
        return usuario;
    }

    public static ArrayList<String> obtenerLinea(String nomArchivo) {
        String filePath = "nombres/" + nomArchivo;

        // Lista para almacenar los nombres
        ArrayList<String> nombres = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            // Leer el archivo línea por línea
            while ((linea = br.readLine()) != null) {
                // Añadir cada línea (nombre) a la lista
                nombres.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nombres;
    }
    
}
