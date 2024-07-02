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
import com.killa.sierravp.repository.Universidad;
import com.killa.sierravp.service.FacultadService;
import com.killa.sierravp.util.MapaCursos;
import com.killa.sierravp.util.ObtenerCursos;
import com.killa.sierravp.util.TipoNota;
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
 * @author HITV
 */
public class GenerarFacultades {

    public static Universidad GenerarFacultadesCompletas(String nombreFacultad) {
        ArrayList<String> nombresMujer = obtenerLinea("Mujeres.txt");
        ArrayList<String> nombresVaron = obtenerLinea("Hombres.txt");
        ArrayList<String> apellidos = obtenerLinea("Apellidos.txt");

        FacultadService facultadService = new FacultadService();
        Facultad facultad = facultadService.obtenerFacultadPorNombreConEscuelas(nombreFacultad);

        if (facultad == null) {
            throw new RuntimeException("Facultad not found.");
        }

        Universidad universidad = new Universidad();
        universidad.agregarFacultad(nombreFacultad);
        Universidad.FacultadData facultadData = universidad.obtenerFacultad(nombreFacultad);

        for (EscuelaProfesional ep : facultad.getEp()) {
            facultadData.agregarEscuela(ep);
        }

        ArrayList<Clase> ordenadas = new ArrayList<>(251);

        Curso cursonuncaUsado = new Curso();
        cursonuncaUsado.setNombre("Sexologia De Pollos");
        Clase clasenuncaUsada = new Clase(cursonuncaUsado);
        ordenadas.add(clasenuncaUsada);

        MapaCursos mc = ObtenerCursos.obtenerCursosDeFacultad("FDok.txt");

        byte ciclo = 1;
        int numeroCurso = 1;

        Map<Byte, Set<Clase>> clasesPorCiclo = new HashMap<>();

        for (Universidad.EscuelaData escuelaData : facultadData.getEscuelas().values()) {
            for (int i = 1; i <= 50; i++) {
                String nombreCurso = mc.get(nombreFacultad, ciclo, numeroCurso);
                Curso c = new Curso();
                c.setNombre(nombreCurso);
                Set<Clase> clasesDelCurso = new HashSet<>();
                c.setClases(clasesDelCurso);

                for (int j = 1; j <= 5; j++) {
                    Clase clase = new Clase();
                    clase.setCurso(c);

                    Profesor profe = GenerarNombresUsuario(new Profesor(), nombresMujer, nombresVaron, apellidos, i * 5 + j);
                    EscuelaProfesional ep = escuelaData.getEscuela();

                    if (ep == null) {
                        throw new RuntimeException("EscuelaProfesional no encontrada.");
                    }
                    profe.setEps(Set.of(ep));
                    profe.generarCorreo();

                    clase.setProfesor(profe);
                    clasesDelCurso.add(clase);
                    ordenadas.add(clase);
                    clasesPorCiclo.computeIfAbsent(ciclo, k -> new HashSet<>()).add(clase);

                    escuelaData.agregarProfesor(profe);
                    escuelaData.agregarClase(clase);
                }

                numeroCurso++;
                if (numeroCurso == 6) {
                    numeroCurso = 1;
                    ciclo++;
                }

                escuelaData.agregarCurso(c);
            }

            for (byte cicloActual = 1; cicloActual <= 10; cicloActual++) {
                for (int grupo = 1; grupo <= 5; grupo++) {
                    for (int k = 1; k <= 40; k++) {
                        Alumno alumno = GenerarNombresUsuario(new Alumno(), nombresMujer, nombresVaron, apellidos, k);
                        alumno.generarCorreo();
                        alumno.setFacultad(facultad);
                        alumno.setEp(escuelaData.getEscuela());

                        BigFiveScores bfs = new BigFiveScores();
                        bfs.setAlumno(alumno);

                        InteresesAcademicos ia = new InteresesAcademicos();
                        ia.setAlumno(alumno);

                        alumno.setCiclo(cicloActual);
                        alumno.setBfScores(bfs);
                        alumno.setInteresesAcademicos(ia);

                        Set<Clase> clasesAlumno = new HashSet<>();
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

                        for (Clase cl : clasesAlumno) {
                            Curso curso = cl.getCurso();
                            alumno.setNotas(generarNotaAlumno(alumno, curso, cl, grupo));
                        }

                        escuelaData.agregarAlumno(alumno);
                    }
                }
            }
        }
        return universidad;
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
            int r = random.nextInt(nombreMujer.size());
            String nomb1 = nombreMujer.get(r);
            usuario.setPrimerNombre(nomb1);
            int r2 = random.nextInt(nombreMujer.size());
            while (nomb1.equals(nombreMujer.get(r2))) {
                r2 = random.nextInt(nombreMujer.size());
            }
            usuario.setSegundoNombre(nombreMujer.get(r2));
            usuario.setPrimerApellido(apellidos.get(r));
            usuario.setSegundoApellido(apellidos.get(r2));
        } else {
            int r = random.nextInt(nombreVaron.size());
            String nomb1 = nombreVaron.get(r);
            usuario.setPrimerNombre(nomb1);
            int r2 = random.nextInt(nombreVaron.size());
            while (nomb1.equals(nombreMujer.get(r2))) {
                r2 = random.nextInt(nombreVaron.size());
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
